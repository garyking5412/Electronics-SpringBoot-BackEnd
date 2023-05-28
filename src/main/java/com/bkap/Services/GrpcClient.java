package com.bkap.Services;

import com.example.electronicsspringbootclientservice.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GrpcClient {
    public com.example.electronicsspringbootclientservice.PingResponse ping(com.example.electronicsspringbootclientservice.PingRequest request) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 3004).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);
        com.example.electronicsspringbootclientservice.PingResponse response = stub.ping(request);
        channel.shutdown();
        return response;
    }

    public com.example.electronicsspringbootclientservice.GetProductByIdResponse getProductInfo(com.example.electronicsspringbootclientservice.GetProductByIdRequest request) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 3004).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);
        com.example.electronicsspringbootclientservice.GetProductByIdResponse response = stub.getProductById(request);
        channel.shutdown();
        return response;
    }
}
