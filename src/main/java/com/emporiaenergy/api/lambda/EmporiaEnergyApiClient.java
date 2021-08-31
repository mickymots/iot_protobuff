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

public class EmporiaEnergyApiClient  {
	private final PartnerApiGrpc.PartnerApiBlockingStub blockingStub;
	private final S3OutputStream outputStream ;
	/** Construct client for accessing server using the existing channel. */
	public EmporiaEnergyApiClient(Channel channel) {
		this.outputStream = null;
		blockingStub = PartnerApiGrpc.newBlockingStub(channel);
	}

	/** Construct client for accessing server using the existing channel. */
	public EmporiaEnergyApiClient(Channel channel, S3OutputStream outputStream) {
		blockingStub = PartnerApiGrpc.newBlockingStub(channel);
		this.outputStream = outputStream;
	}

	private void write(String data){
		if(outputStream != null){
			outputStream.write(data.getBytes());
			outputStream.write("\n".getBytes());
		}else{
			System.out.println(data);
		}
	}

	public void apiCalls(final String partnerEmail, final String partnerPw, final int days) {
		
		try {
			// authenticate with partner email and PW
			AuthenticationRequest request = AuthenticationRequest.newBuilder().setPartnerEmail(partnerEmail)
					.setPassword(partnerPw).build();
			AuthenticationResponse authResponse = blockingStub.authenticate(request);
			if (ResultStatus.VALID != authResponse.getResultStatus()) {
				this.write(String.format("authorization failed for %s / %s with %s", partnerEmail, partnerPw,
						authResponse.getResultStatus()));

				return;
			}

			final String authToken = authResponse.getAuthToken();
			// outputStream.write("test".getBytes());
			this.write(new String("auth status: " + authResponse.getResultStatus() + "   token: " + authToken));

			// get list of devices managed by partner
			DeviceInventoryRequest inventoryRequest = DeviceInventoryRequest.newBuilder().setAuthToken(authToken)
					.build();
			DeviceInventoryResponse inventoryResponse = blockingStub.getDevices(inventoryRequest);
			if (ResultStatus.VALID != inventoryResponse.getResultStatus()) {
				this.write(String.format("authorization error %s for %s", inventoryResponse.getResultStatus(),
						partnerEmail));

			}

			// display device ids and models
			final List<Device> deviceList = inventoryResponse.getDevicesList();
			for (Device d : deviceList) {
				this.write(String.format("	%24s; %8s; FW %s; app use %s; solar %s; name %s; Lat/Long %f/%f",
						d.getManufacturerDeviceId(), d.getModel(), d.getFirmware(), d.getLastAppConnectTime(),
						d.getSolar(), d.getDeviceName(), d.getLatitude(), d.getLongitude()));

			}

			// display device information, grouping devices by model using Java streams
			this.write(inventoryResponse.getDevicesCount() + " devices: " + inventoryResponse.getDevicesList()
					.stream().map(d -> d.getManufacturerDeviceId()).collect(Collectors.toList()));

			inventoryResponse.getDevicesList().stream()
					.sorted(Comparator.comparing(DeviceInventoryResponse.Device::getModel))
					.forEach(d -> this.write(String.format(
							"	%24s; %8s; FW %s; app use %s; solar %s; name %s; Lat/Long %f/%f; Customers: %s; Channels: %s \n",
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
				this.write(
						String.format("authorization error %s for %s", outletStatus.getResultStatus(), partnerEmail));
				return;
			}
			this.write("\n" + outletStatus.getOutletStatusCount() + " Outlets");
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
				this.write(
						String.format("authorization error %s for %s", usageResponse.getResultStatus(), partnerEmail));
				return;
			}

			usageResponse.getDeviceUsageList().stream().forEach(u -> {
				this.write(String.format("Usage: %s  scale: %s", u.getManufacturerDeviceId(), u.getScale()));

				for (int i = 0; i < u.getBucketEpochSecondsCount(); ++i) {
					StringBuilder sb = new StringBuilder();
					sb.append(String.format("	%s:", Instant.ofEpochSecond(u.getBucketEpochSeconds(i))));

					
					for (int channelIndex = 0; channelIndex < u.getChannelUsageCount(); ++channelIndex)
						sb.append(String.format("  (%d) %f;", u.getChannelUsage(channelIndex).getChannel(),
								u.getChannelUsage(channelIndex).getUsage(i)));
					this.write(sb.toString());
				}
			});

		} catch (StatusRuntimeException e) {
			e.printStackTrace();
			this.write("WARNING: RPC failed: " + e.getMessage());
			return;
		}
		finally{
			System.out.println("API Request Completed");
		}
	}

}
