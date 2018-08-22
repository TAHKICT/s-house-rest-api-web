package com.shouse.restapi.web.communicators;


import shouse.core.node.request.Request;
import shouse.core.node.response.Response;

public interface CommunicatorWithBrain {

    public Response sendRequest(Request request);

    public Response receiveResponse();

    public boolean hasResponse();

}
