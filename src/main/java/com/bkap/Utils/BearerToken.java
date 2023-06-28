package com.bkap.Utils;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;

import java.util.concurrent.Executor;

public class BearerToken extends CallCredentials {

    private String value;
    public BearerToken(String token){
        this.value = token;
    }

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
        executor.execute(()->{
            try {
                Metadata headers = new Metadata();
                headers.put(Constants.AUTHORIZATION_METADATA_KEY,String.format("%s %s",Constants.BEARER_TYPE,value));
                metadataApplier.apply(headers);
            }
            catch (Throwable throwable){
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(throwable));
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {

    }
}
