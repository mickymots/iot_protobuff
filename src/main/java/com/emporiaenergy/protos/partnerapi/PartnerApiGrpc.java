package com.emporiaenergy.protos.partnerapi;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * 
 * Supported API calls for partner access to devices and cloud data
 *
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.11.0)",
    comments = "Source: partner_api.proto")
public final class PartnerApiGrpc {

  private PartnerApiGrpc() {}

  public static final String SERVICE_NAME = "protos.PartnerApi";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getAuthenticateMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.AuthenticationRequest,
      com.emporiaenergy.protos.partnerapi.AuthenticationResponse> METHOD_AUTHENTICATE = getAuthenticateMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.AuthenticationRequest,
      com.emporiaenergy.protos.partnerapi.AuthenticationResponse> getAuthenticateMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.AuthenticationRequest,
      com.emporiaenergy.protos.partnerapi.AuthenticationResponse> getAuthenticateMethod() {
    return getAuthenticateMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.AuthenticationRequest,
      com.emporiaenergy.protos.partnerapi.AuthenticationResponse> getAuthenticateMethodHelper() {
    io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.AuthenticationRequest, com.emporiaenergy.protos.partnerapi.AuthenticationResponse> getAuthenticateMethod;
    if ((getAuthenticateMethod = PartnerApiGrpc.getAuthenticateMethod) == null) {
      synchronized (PartnerApiGrpc.class) {
        if ((getAuthenticateMethod = PartnerApiGrpc.getAuthenticateMethod) == null) {
          PartnerApiGrpc.getAuthenticateMethod = getAuthenticateMethod = 
              io.grpc.MethodDescriptor.<com.emporiaenergy.protos.partnerapi.AuthenticationRequest, com.emporiaenergy.protos.partnerapi.AuthenticationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.PartnerApi", "Authenticate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.AuthenticationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.AuthenticationResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PartnerApiMethodDescriptorSupplier("Authenticate"))
                  .build();
          }
        }
     }
     return getAuthenticateMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetDevicesMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest,
      com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse> METHOD_GET_DEVICES = getGetDevicesMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest,
      com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse> getGetDevicesMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest,
      com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse> getGetDevicesMethod() {
    return getGetDevicesMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest,
      com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse> getGetDevicesMethodHelper() {
    io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest, com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse> getGetDevicesMethod;
    if ((getGetDevicesMethod = PartnerApiGrpc.getGetDevicesMethod) == null) {
      synchronized (PartnerApiGrpc.class) {
        if ((getGetDevicesMethod = PartnerApiGrpc.getGetDevicesMethod) == null) {
          PartnerApiGrpc.getGetDevicesMethod = getGetDevicesMethod = 
              io.grpc.MethodDescriptor.<com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest, com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.PartnerApi", "GetDevices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PartnerApiMethodDescriptorSupplier("GetDevices"))
                  .build();
          }
        }
     }
     return getGetDevicesMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetUsageDataMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceUsageRequest,
      com.emporiaenergy.protos.partnerapi.DeviceUsageResponse> METHOD_GET_USAGE_DATA = getGetUsageDataMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceUsageRequest,
      com.emporiaenergy.protos.partnerapi.DeviceUsageResponse> getGetUsageDataMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceUsageRequest,
      com.emporiaenergy.protos.partnerapi.DeviceUsageResponse> getGetUsageDataMethod() {
    return getGetUsageDataMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceUsageRequest,
      com.emporiaenergy.protos.partnerapi.DeviceUsageResponse> getGetUsageDataMethodHelper() {
    io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceUsageRequest, com.emporiaenergy.protos.partnerapi.DeviceUsageResponse> getGetUsageDataMethod;
    if ((getGetUsageDataMethod = PartnerApiGrpc.getGetUsageDataMethod) == null) {
      synchronized (PartnerApiGrpc.class) {
        if ((getGetUsageDataMethod = PartnerApiGrpc.getGetUsageDataMethod) == null) {
          PartnerApiGrpc.getGetUsageDataMethod = getGetUsageDataMethod = 
              io.grpc.MethodDescriptor.<com.emporiaenergy.protos.partnerapi.DeviceUsageRequest, com.emporiaenergy.protos.partnerapi.DeviceUsageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.PartnerApi", "GetUsageData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.DeviceUsageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.DeviceUsageResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PartnerApiMethodDescriptorSupplier("GetUsageData"))
                  .build();
          }
        }
     }
     return getGetUsageDataMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getGetOutletStatusMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceListRequest,
      com.emporiaenergy.protos.partnerapi.OutletStatusResponse> METHOD_GET_OUTLET_STATUS = getGetOutletStatusMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceListRequest,
      com.emporiaenergy.protos.partnerapi.OutletStatusResponse> getGetOutletStatusMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceListRequest,
      com.emporiaenergy.protos.partnerapi.OutletStatusResponse> getGetOutletStatusMethod() {
    return getGetOutletStatusMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceListRequest,
      com.emporiaenergy.protos.partnerapi.OutletStatusResponse> getGetOutletStatusMethodHelper() {
    io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.DeviceListRequest, com.emporiaenergy.protos.partnerapi.OutletStatusResponse> getGetOutletStatusMethod;
    if ((getGetOutletStatusMethod = PartnerApiGrpc.getGetOutletStatusMethod) == null) {
      synchronized (PartnerApiGrpc.class) {
        if ((getGetOutletStatusMethod = PartnerApiGrpc.getGetOutletStatusMethod) == null) {
          PartnerApiGrpc.getGetOutletStatusMethod = getGetOutletStatusMethod = 
              io.grpc.MethodDescriptor.<com.emporiaenergy.protos.partnerapi.DeviceListRequest, com.emporiaenergy.protos.partnerapi.OutletStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.PartnerApi", "GetOutletStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.DeviceListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.OutletStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PartnerApiMethodDescriptorSupplier("GetOutletStatus"))
                  .build();
          }
        }
     }
     return getGetOutletStatusMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getSetOutletStatusMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.OutletStatusRequest,
      com.emporiaenergy.protos.partnerapi.OutletStatusResponse> METHOD_SET_OUTLET_STATUS = getSetOutletStatusMethodHelper();

  private static volatile io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.OutletStatusRequest,
      com.emporiaenergy.protos.partnerapi.OutletStatusResponse> getSetOutletStatusMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.OutletStatusRequest,
      com.emporiaenergy.protos.partnerapi.OutletStatusResponse> getSetOutletStatusMethod() {
    return getSetOutletStatusMethodHelper();
  }

  private static io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.OutletStatusRequest,
      com.emporiaenergy.protos.partnerapi.OutletStatusResponse> getSetOutletStatusMethodHelper() {
    io.grpc.MethodDescriptor<com.emporiaenergy.protos.partnerapi.OutletStatusRequest, com.emporiaenergy.protos.partnerapi.OutletStatusResponse> getSetOutletStatusMethod;
    if ((getSetOutletStatusMethod = PartnerApiGrpc.getSetOutletStatusMethod) == null) {
      synchronized (PartnerApiGrpc.class) {
        if ((getSetOutletStatusMethod = PartnerApiGrpc.getSetOutletStatusMethod) == null) {
          PartnerApiGrpc.getSetOutletStatusMethod = getSetOutletStatusMethod = 
              io.grpc.MethodDescriptor.<com.emporiaenergy.protos.partnerapi.OutletStatusRequest, com.emporiaenergy.protos.partnerapi.OutletStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.PartnerApi", "SetOutletStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.OutletStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.emporiaenergy.protos.partnerapi.OutletStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PartnerApiMethodDescriptorSupplier("SetOutletStatus"))
                  .build();
          }
        }
     }
     return getSetOutletStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PartnerApiStub newStub(io.grpc.Channel channel) {
    return new PartnerApiStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PartnerApiBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PartnerApiBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PartnerApiFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PartnerApiFutureStub(channel);
  }

  /**
   * <pre>
   * 
   * Supported API calls for partner access to devices and cloud data
   *
   * </pre>
   */
  public static abstract class PartnerApiImplBase implements io.grpc.BindableService {

    /**
     */
    public void authenticate(com.emporiaenergy.protos.partnerapi.AuthenticationRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.AuthenticationResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAuthenticateMethodHelper(), responseObserver);
    }

    /**
     */
    public void getDevices(com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDevicesMethodHelper(), responseObserver);
    }

    /**
     */
    public void getUsageData(com.emporiaenergy.protos.partnerapi.DeviceUsageRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.DeviceUsageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUsageDataMethodHelper(), responseObserver);
    }

    /**
     */
    public void getOutletStatus(com.emporiaenergy.protos.partnerapi.DeviceListRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.OutletStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetOutletStatusMethodHelper(), responseObserver);
    }

    /**
     */
    public void setOutletStatus(com.emporiaenergy.protos.partnerapi.OutletStatusRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.OutletStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSetOutletStatusMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAuthenticateMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.emporiaenergy.protos.partnerapi.AuthenticationRequest,
                com.emporiaenergy.protos.partnerapi.AuthenticationResponse>(
                  this, METHODID_AUTHENTICATE)))
          .addMethod(
            getGetDevicesMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest,
                com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse>(
                  this, METHODID_GET_DEVICES)))
          .addMethod(
            getGetUsageDataMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.emporiaenergy.protos.partnerapi.DeviceUsageRequest,
                com.emporiaenergy.protos.partnerapi.DeviceUsageResponse>(
                  this, METHODID_GET_USAGE_DATA)))
          .addMethod(
            getGetOutletStatusMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.emporiaenergy.protos.partnerapi.DeviceListRequest,
                com.emporiaenergy.protos.partnerapi.OutletStatusResponse>(
                  this, METHODID_GET_OUTLET_STATUS)))
          .addMethod(
            getSetOutletStatusMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                com.emporiaenergy.protos.partnerapi.OutletStatusRequest,
                com.emporiaenergy.protos.partnerapi.OutletStatusResponse>(
                  this, METHODID_SET_OUTLET_STATUS)))
          .build();
    }
  }

  /**
   * <pre>
   * 
   * Supported API calls for partner access to devices and cloud data
   *
   * </pre>
   */
  public static final class PartnerApiStub extends io.grpc.stub.AbstractStub<PartnerApiStub> {
    private PartnerApiStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PartnerApiStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PartnerApiStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PartnerApiStub(channel, callOptions);
    }

    /**
     */
    public void authenticate(com.emporiaenergy.protos.partnerapi.AuthenticationRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.AuthenticationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuthenticateMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDevices(com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDevicesMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUsageData(com.emporiaenergy.protos.partnerapi.DeviceUsageRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.DeviceUsageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUsageDataMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOutletStatus(com.emporiaenergy.protos.partnerapi.DeviceListRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.OutletStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetOutletStatusMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setOutletStatus(com.emporiaenergy.protos.partnerapi.OutletStatusRequest request,
        io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.OutletStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetOutletStatusMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * 
   * Supported API calls for partner access to devices and cloud data
   *
   * </pre>
   */
  public static final class PartnerApiBlockingStub extends io.grpc.stub.AbstractStub<PartnerApiBlockingStub> {
    private PartnerApiBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PartnerApiBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PartnerApiBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PartnerApiBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.emporiaenergy.protos.partnerapi.AuthenticationResponse authenticate(com.emporiaenergy.protos.partnerapi.AuthenticationRequest request) {
      return blockingUnaryCall(
          getChannel(), getAuthenticateMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse getDevices(com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDevicesMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.emporiaenergy.protos.partnerapi.DeviceUsageResponse getUsageData(com.emporiaenergy.protos.partnerapi.DeviceUsageRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetUsageDataMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.emporiaenergy.protos.partnerapi.OutletStatusResponse getOutletStatus(com.emporiaenergy.protos.partnerapi.DeviceListRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetOutletStatusMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public com.emporiaenergy.protos.partnerapi.OutletStatusResponse setOutletStatus(com.emporiaenergy.protos.partnerapi.OutletStatusRequest request) {
      return blockingUnaryCall(
          getChannel(), getSetOutletStatusMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * 
   * Supported API calls for partner access to devices and cloud data
   *
   * </pre>
   */
  public static final class PartnerApiFutureStub extends io.grpc.stub.AbstractStub<PartnerApiFutureStub> {
    private PartnerApiFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PartnerApiFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PartnerApiFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PartnerApiFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.emporiaenergy.protos.partnerapi.AuthenticationResponse> authenticate(
        com.emporiaenergy.protos.partnerapi.AuthenticationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAuthenticateMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse> getDevices(
        com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDevicesMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.emporiaenergy.protos.partnerapi.DeviceUsageResponse> getUsageData(
        com.emporiaenergy.protos.partnerapi.DeviceUsageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUsageDataMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.emporiaenergy.protos.partnerapi.OutletStatusResponse> getOutletStatus(
        com.emporiaenergy.protos.partnerapi.DeviceListRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetOutletStatusMethodHelper(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.emporiaenergy.protos.partnerapi.OutletStatusResponse> setOutletStatus(
        com.emporiaenergy.protos.partnerapi.OutletStatusRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSetOutletStatusMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTHENTICATE = 0;
  private static final int METHODID_GET_DEVICES = 1;
  private static final int METHODID_GET_USAGE_DATA = 2;
  private static final int METHODID_GET_OUTLET_STATUS = 3;
  private static final int METHODID_SET_OUTLET_STATUS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PartnerApiImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PartnerApiImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTHENTICATE:
          serviceImpl.authenticate((com.emporiaenergy.protos.partnerapi.AuthenticationRequest) request,
              (io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.AuthenticationResponse>) responseObserver);
          break;
        case METHODID_GET_DEVICES:
          serviceImpl.getDevices((com.emporiaenergy.protos.partnerapi.DeviceInventoryRequest) request,
              (io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.DeviceInventoryResponse>) responseObserver);
          break;
        case METHODID_GET_USAGE_DATA:
          serviceImpl.getUsageData((com.emporiaenergy.protos.partnerapi.DeviceUsageRequest) request,
              (io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.DeviceUsageResponse>) responseObserver);
          break;
        case METHODID_GET_OUTLET_STATUS:
          serviceImpl.getOutletStatus((com.emporiaenergy.protos.partnerapi.DeviceListRequest) request,
              (io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.OutletStatusResponse>) responseObserver);
          break;
        case METHODID_SET_OUTLET_STATUS:
          serviceImpl.setOutletStatus((com.emporiaenergy.protos.partnerapi.OutletStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.emporiaenergy.protos.partnerapi.OutletStatusResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PartnerApiBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PartnerApiBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.emporiaenergy.protos.partnerapi.PartnerApiProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PartnerApi");
    }
  }

  private static final class PartnerApiFileDescriptorSupplier
      extends PartnerApiBaseDescriptorSupplier {
    PartnerApiFileDescriptorSupplier() {}
  }

  private static final class PartnerApiMethodDescriptorSupplier
      extends PartnerApiBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PartnerApiMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PartnerApiGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PartnerApiFileDescriptorSupplier())
              .addMethod(getAuthenticateMethodHelper())
              .addMethod(getGetDevicesMethodHelper())
              .addMethod(getGetUsageDataMethodHelper())
              .addMethod(getGetOutletStatusMethodHelper())
              .addMethod(getSetOutletStatusMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
