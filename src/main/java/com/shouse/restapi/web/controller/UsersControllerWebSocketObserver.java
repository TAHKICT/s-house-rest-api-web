package com.shouse.restapi.web.controller;

import com.shouse.restapi.web.domain.WebSocketMessage;

public interface UsersControllerWebSocketObserver {

    public WebSocketMessage webSocketMessage(WebSocketMessage webSocketMessage);
}
