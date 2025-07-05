package com.aks.billingserv.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import com.aks.billingserv.enums.AccountStatus;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

//    @Override
//    public void CreateBillingAccount(billing.BillingRequest request,
//                                     io.grpc.stub.StreamObserver<billing.BillingResponse> responseObserver){
//        Logger logger = LoggerFactory.getLogger(this.getClass());
//        logger.info("Create BillingAccount");
//        BillingResponse billingResponse = BillingResponse.newBuilder()
//                .setAccountId("1234")
//                .setStatus(AccountStatus.CREATED.getValue())
//                .build();
//        responseObserver.onNext(billingResponse);
//        responseObserver.onCompleted();
//
//    }

    @Override
    public void createBillingAccount(billing.BillingRequest request,
                                     io.grpc.stub.StreamObserver<billing.BillingResponse> responseObserver){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Create BillingAccount with patientId "+request.getPatientId());
        BillingResponse billingResponse = BillingResponse.newBuilder()
                .setAccountId("1234")
                .setStatus(AccountStatus.CREATED.getValue())
                .build();
        responseObserver.onNext(billingResponse);
        responseObserver.onCompleted();

    }

}
