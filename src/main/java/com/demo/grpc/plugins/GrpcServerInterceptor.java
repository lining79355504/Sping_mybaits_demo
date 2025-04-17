package com.demo.grpc.plugins;

import com.demo.file.ExcelExportUtils;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GrpcServerInterceptor implements ServerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(GrpcServerInterceptor.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        // 在这里可以添加拦截逻辑，比如打印请求信息、修改请求参数等
        System.out.println("Intercepting gRPC call: " + serverCall.getMethodDescriptor().getFullMethodName());

        // 包装原始的 ServerCall
        ServerCall<ReqT, RespT> loggingCall = new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(serverCall) {
            @Override
            public void sendMessage(RespT message) {
                // 捕获并打印响应数据
                System.out.println("Response: " + message);
                super.sendMessage(message); // 继续发送响应
            }
        };

        ServerCall.Listener<ReqT> listener = serverCallHandler.startCall(loggingCall, metadata);
        // 调用下一个拦截器或处理器
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {

            @Override
            public void onMessage(ReqT message) {
                // 捕获请求参数
                System.out.println("Request: " + message);
                super.onMessage(message);
            }

            @Override
            public void onHalfClose() {
                try {
                    super.onHalfClose();
                } catch (Throwable e) {
                    System.out.println("onHalfClose error: " + e.getMessage());
                    log.error("onHalfClose error: ", e);
                }
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onReady() {
                super.onReady();
            }
        };

    }
}
