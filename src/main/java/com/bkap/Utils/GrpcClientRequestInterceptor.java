package com.bkap.Utils;

import io.grpc.*;

public class GrpcClientRequestInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor,callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
//                String jwt = JWTUtil.getCompactJWT();
//                BearerToken token = new BearerToken(jwt);
                headers.put(Constants.AUTHORIZATION_METADATA_KEY,String.format("%s %s",Constants.BEARER_TYPE,Constants.JWT_SIGNING_KEY));
                super.start(responseListener, headers);
            }
        };
    }
}
