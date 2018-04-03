package com.shouse.restapi.web.service;

import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.domain.WebSocketMessage;

import java.util.List;

public interface UserService {

    public List<NodeInfoMessage> getActiveNodes(String nodeType);

    public WebSocketMessage handleWebSocketRequest(WebSocketMessage webSocketMessage);

}
