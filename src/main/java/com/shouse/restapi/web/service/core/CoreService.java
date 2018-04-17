package com.shouse.restapi.web.service.core;

import com.shouse.restapi.web.domain.NodeParamChangeEvent;

public interface CoreService {

    public void handleRequestFromCore(NodeParamChangeEvent nodeParamChangeEvent);

    public void sendRequestToCore(NodeParamChangeEvent nodeParamChangeEvent);
}
