package com.shouse.restapi.web.service.core;

import com.shouse.restapi.web.domain.WebSocketMessage;

public interface CoreService {

    public void handleRequestFromCore(WebSocketMessage webSocketMessage);

    public void sendRequestToCore(WebSocketMessage webSocketMessage);
}
