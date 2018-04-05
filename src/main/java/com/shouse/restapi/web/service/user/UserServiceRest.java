package com.shouse.restapi.web.service.user;

import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.domain.WebSocketMessage;
import com.shouse.restapi.web.service.core.CoreService;
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
    CoreService coreService;

    @Override
    public List<NodeInfoMessage> getActiveNodes(String nodeType) {
        ResponseEntity<NodeInfoMessage[]> responseEntity = restTemplate.getForEntity("http://localhost:8181/core-rest-api/for-web-application/get-active-nodes/"+nodeType, NodeInfoMessage[].class);
        NodeInfoMessage[] nodeInfoMessages = responseEntity.getBody();
        System.out.println(Arrays.asList(nodeInfoMessages));
        return Arrays.asList(nodeInfoMessages);
    }

    @Override
    public WebSocketMessage handleWebSocketRequest(WebSocketMessage webSocketMessage) {
        coreService.sendRequestToCore(webSocketMessage);
        return webSocketMessage;
    }
}
