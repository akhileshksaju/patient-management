package com.aks.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    public final BillingServiceGrpc.BillingServiceBlockingStub stub;
    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.server.port:9001}") int serverPort){

        log.info("Initializing BillingServiceGrpc.BillingServiceBlockingStub with address:{}, port:{}", serverAddress, serverPort);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext().build();
        stub = BillingServiceGrpc.newBlockingStub(channel);

    }

    public BillingResponse createBillinAccount(String patientId,String name,String email){
        BillingRequest request = BillingRequest.newBuilder().setName(name).setEmail(email).build();
        BillingResponse response = stub.createBillingAccount(request);
        return response;
    }

}
