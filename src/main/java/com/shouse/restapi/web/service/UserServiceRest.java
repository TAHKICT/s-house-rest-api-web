package com.shouse.restapi.web.service;

import com.shouse.restapi.web.controller.UsersControllerWebSocketObserver;
import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.domain.WebSocketMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class UserServiceRest implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UsersControllerWebSocketObserver usersControllerWebSocketObserver;

    @Override
    public List<NodeInfoMessage> getActiveNodes(String nodeType) {
        ResponseEntity<NodeInfoMessage[]> responseEntity = restTemplate.getForEntity("http://localhost:8181/core-rest-api/for-web-application/get-active-nodes/"+nodeType, NodeInfoMessage[].class);
        NodeInfoMessage[] nodeInfoMessages = responseEntity.getBody();
        return Arrays.asList(nodeInfoMessages);
    }

    @Override
    public WebSocketMessage handleWebSocketRequest(WebSocketMessage webSocketMessage) {
        return webSocketMessage;
    }

    @Override
    public void handleRequestFromCore(WebSocketMessage webSocketMessage) {
        log.info("UserServiceRest. handleRequestFromCore. Incoming webSocketMessage: " + webSocketMessage);
        usersControllerWebSocketObserver.webSocketMessage(webSocketMessage);
    }

}
