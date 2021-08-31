package com.emporiaenergy.api.lambda;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.io.*;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import com.amazonaws.regions.Regions;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaHandler implements RequestHandler<Object, Object> {

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
		String bucket = System.getenv("bucket");
		String fileName = System.getenv("fileName");

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();

		S3OutputStream outputStream = new S3OutputStream(s3Client, bucket, fileName);

		// Create a communication channel to the server, known as a Channel. Channels
		// are thread-safe
		// and reusable. It is common to create channels at the beginning of your
		// application and reuse
		// them until the application shuts down.
		ManagedChannel channel = ManagedChannelBuilder.forTarget(host + ":" + port).usePlaintext().build();

		System.out.println(String.format("Creating EmporiaEnergyApiClient using gRPC service %s:%d", host, port));

		try {
			EmporiaEnergyApiClient client = new EmporiaEnergyApiClient(channel, outputStream);
			client.apiCalls(partnerEmail, partnerPw, days);
		} finally {
			outputStream.close();
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
		System.out.println("Lambda Handler");
		String partnerEmail = "phart@sustainergy.ca";
		String partnerPw = "hello12345";
		String serial_number = "";
		S3OutputStream outputStream = null;
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
