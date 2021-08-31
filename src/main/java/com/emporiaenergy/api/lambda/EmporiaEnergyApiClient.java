package com.emporiaenergy.api.lambda;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.io.*;

import com.emporiaenergy.protos.partnerapi.ResultStatus;
import com.emporiaenergy.protos.partnerapi.AuthenticationRequest;
import com.emporiaenergy.protos.partnerapi.AuthenticationResponse;
import com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest;
import com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse;
import com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse.Device;
import com.emporiaenergy.protos.partnerapi.DeviceListRequest;
import com.emporiaenergy.protos.partnerapi.DeviceListRequest.Builder;
import com.emporiaenergy.protos.partnerapi.DeviceUsageRequest;
import com.emporiaenergy.protos.partnerapi.DeviceUsageResponse;
import com.emporiaenergy.protos.partnerapi.OutletStatusResponse;
import com.emporiaenergy.protos.partnerapi.PartnerApiGrpc;
import com.emporiaenergy.protos.partnerapi.UsageChannel;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class EmporiaEnergyApiClient implements RequestHandler<Object, Object> {
	private final PartnerApiGrpc.PartnerApiBlockingStub blockingStub;

	/** Construct client for accessing server using the existing channel. */
	public EmporiaEnergyApiClient(Channel channel) {
		
		blockingStub = PartnerApiGrpc.newBlockingStub(channel);
	}

	public void apiCalls(final String partnerEmail, final String partnerPw, final int days) {
		
		try {
			// authenticate with partner email and PW
			AuthenticationRequest request = AuthenticationRequest.newBuilder().setPartnerEmail(partnerEmail)
					.setPassword(partnerPw).build();
			AuthenticationResponse authResponse = blockingStub.authenticate(request);
			if (ResultStatus.VALID != authResponse.getResultStatus()) {
				System.out.println(String.format("authorization failed for %s / %s with %s", partnerEmail, partnerPw,
						authResponse.getResultStatus()));

				return;
			}

			final String authToken = authResponse.getAuthToken();
			System.out.println("auth status: " + authResponse.getResultStatus() + "   token: " + authToken);

			// get list of devices managed by partner
			DeviceInventoryRequest inventoryRequest = DeviceInventoryRequest.newBuilder().setAuthToken(authToken)
					.build();
			DeviceInventoryResponse inventoryResponse = blockingStub.getDevices(inventoryRequest);
			if (ResultStatus.VALID != inventoryResponse.getResultStatus()) {
				System.out.println(String.format("authorization error %s for %s", inventoryResponse.getResultStatus(),
						partnerEmail));

			}

			// display device ids and models
			final List<Device> deviceList = inventoryResponse.getDevicesList();
			for (Device d : deviceList) {
				System.out.println(String.format("	%24s; %8s; FW %s; app use %s; solar %s; name %s; Lat/Long %f/%f",
						d.getManufacturerDeviceId(), d.getModel(), d.getFirmware(), d.getLastAppConnectTime(),
						d.getSolar(), d.getDeviceName(), d.getLatitude(), d.getLongitude()));

			}

			// display device information, grouping devices by model using Java streams
			System.out.println(inventoryResponse.getDevicesCount() + " devices: " + inventoryResponse.getDevicesList()
					.stream().map(d -> d.getManufacturerDeviceId()).collect(Collectors.toList()));

			inventoryResponse.getDevicesList().stream()
					.sorted(Comparator.comparing(DeviceInventoryResponse.Device::getModel))
					.forEach(d -> System.out.println(String.format(
							"	%24s; %8s; FW %s; app use %s; solar %s; name %s; Lat/Long %f/%f; Customers: %s; Channels: %s",
							d.getManufacturerDeviceId(), d.getModel(), d.getFirmware(), d.getLastAppConnectTime(),
							d.getSolar(), d.getDeviceName(), d.getLatitude(), d.getLongitude(),
							d.getCustomerInfoList().stream().map(
									c -> String.format("%s %s (%s)", c.getFirstName(), c.getLastName(), c.getEmail()))
									.collect(Collectors.toList()),
							d.getChannelNamesList())));

			// get outlet status
			Builder deviceRequestBuilder = DeviceListRequest.newBuilder().setAuthToken(authToken);
			// build list with outlet device ids
			inventoryResponse.getDevicesList().stream()
					.forEach(d -> deviceRequestBuilder.addManufacturerDeviceId(d.getManufacturerDeviceId()));
			OutletStatusResponse outletStatus = blockingStub.getOutletStatus(deviceRequestBuilder.build());
			if (ResultStatus.VALID != outletStatus.getResultStatus()) {
				System.out.println(
						String.format("authorization error %s for %s", outletStatus.getResultStatus(), partnerEmail));
				return;
			}
			System.out.println("\n" + outletStatus.getOutletStatusCount() + " Outlets");
			outletStatus.getOutletStatusList().stream().forEach(o -> System.out
					.println(String.format("	%24s; %s", o.getManufacturerDeviceId(), o.getOn() ? "ON" : "OFF")));
			// get usage: 1min bars for last 15 mins of main channels
			long endEpochSeconds = Instant.now().getEpochSecond();
			DeviceUsageRequest.Builder usageRequestBuilder = DeviceUsageRequest.newBuilder().setAuthToken(authToken)
					.setStartEpochSeconds(endEpochSeconds - TimeUnit.DAYS.toSeconds(days))
					.setEndEpochSeconds(endEpochSeconds).setScale("1H").setChannels(UsageChannel.ALL)
					.addAllManufacturerDeviceId(inventoryResponse.getDevicesList().stream()
							.map(d -> d.getManufacturerDeviceId()).collect(Collectors.toList()));
			// addManufacturerDeviceId(serial_number);
			DeviceUsageResponse usageResponse = blockingStub.getUsageData(usageRequestBuilder.build());
			if (ResultStatus.VALID != usageResponse.getResultStatus()) {
				System.out.println(
						String.format("authorization error %s for %s", usageResponse.getResultStatus(), partnerEmail));
				return;
			}

			usageResponse.getDeviceUsageList().stream().forEach(u -> {
				System.out.println(String.format("Usage: %s  scale: %s", u.getManufacturerDeviceId(), u.getScale()));

				for (int i = 0; i < u.getBucketEpochSecondsCount(); ++i) {
					System.out.print(String.format("	%s:", Instant.ofEpochSecond(u.getBucketEpochSeconds(i))));

					for (int channelIndex = 0; channelIndex < u.getChannelUsageCount(); ++channelIndex)
						System.out.print(String.format("  (%d) %f;", u.getChannelUsage(channelIndex).getChannel(),
								u.getChannelUsage(channelIndex).getUsage(i)));

					System.out.println();

				}
			});

		} catch (StatusRuntimeException e) {
			e.printStackTrace();
			System.out.println("WARNING: RPC failed: " + e.getMessage());
			return;
		}
	}

	/**
	 * Demo driver to connect to Emporia Energy's Partner API and return device
	 * information. {@code arg0} is the partner email address {@code arg1} is the
	 * partner password {@code arg2} is Emporia's Partner API host IP address
	 * {@code arg3} is Emporia's Partner API port
	 */
	@Override
	public String handleRequest(Object input, Context context)// throws Exception
	{
		String partnerEmail = "phart@sustainergy.ca";
		String partnerPw = "hello12345";
		String serial_number = "";
		// Access a service running on the local machine on port 50051
		String host = "partner-api.emporiaenergy.com";
		int port = 50051;

	
		int days = 1;
	
		try {
			PrintStream pp = new PrintStream("Output.txt");
			PrintStream consol = System.out;
			System.setOut(pp);
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe);
		}

		// Create a communication channel to the server, known as a Channel. Channels
		// are thread-safe
		// and reusable. It is common to create channels at the beginning of your
		// application and reuse
		// them until the application shuts down.
		ManagedChannel channel = ManagedChannelBuilder.forTarget(host + ":" + port).usePlaintext().build();

		System.out.println(String.format("Creating EmporiaEnergyApiClient using gRPC service %s:%d", host, port));

		try {
			EmporiaEnergyApiClient client = new EmporiaEnergyApiClient(channel);
			client.apiCalls(partnerEmail, partnerPw, days);
		} finally {
			// ManagedChannels use resources like threads and TCP connections. To prevent
			// leaking these
			// resources the channel should be shut down when it will no longer be used. If
			// it may be used
			// again leave it running.
			// channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String partnerEmail = "phart@sustainergy.ca";
		String partnerPw = "hello12345";
		String serial_number = "";
		// Access a service running on the local machine on port 50051
		String host = "partner-api.emporiaenergy.com";
		int port = 50051;

		// partnerEmail = args[0];
		// partnerPw = args[1];
		// serial_number = args[2];
		// int days = Integer.parseInt(args[2]);
		int days = 1;
		// if (args.length > 2)
		// host = args[2];
		// if (args.length > 3)
		// port = Integer.parseInt(args[3]);
		try {
			PrintStream pp = new PrintStream("Output.txt");
			PrintStream consol = System.out;
			System.setOut(pp);
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe);
		}

		// Create a communication channel to the server, known as a Channel. Channels
		// are thread-safe
		// and reusable. It is common to create channels at the beginning of your
		// application and reuse
		// them until the application shuts down.
		ManagedChannel channel = ManagedChannelBuilder.forTarget(host + ":" + port).usePlaintext().build();

		System.out.println(String.format("Creating EmporiaEnergyApiClient using gRPC service %s:%d", host, port));

		try {
			EmporiaEnergyApiClient client = new EmporiaEnergyApiClient(channel);
			client.apiCalls(partnerEmail, partnerPw, days);
		} finally {
			// ManagedChannels use resources like threads and TCP connections. To prevent
			// leaking these
			// resources the channel should be shut down when it will no longer be used. If
			// it may be used
			// again leave it running.
			// channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		}
	}
}
