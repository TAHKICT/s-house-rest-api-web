package com.shouse.restapi.web.service;

import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.domain.WebSocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class UserServiceRest implements UserService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<NodeInfoMessage> getActiveNodes(String nodeType) {
        ResponseEntity<NodeInfoMessage[]> responseEntity = restTemplate.getForEntity("http://localhost:8181/user/getactivenodes/"+nodeType, NodeInfoMessage[].class);
        NodeInfoMessage[] nodeInfoMessages = responseEntity.getBody();
        return Arrays.asList(nodeInfoMessages);
    }

    @Override
    public WebSocketMessage handleWebSocketRequest(WebSocketMessage webSocketMessage) {
        return webSocketMessage;
    }

}
