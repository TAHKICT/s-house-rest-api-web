package com.shouse.restapi.web.service.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shouse.restapi.web.controller.UsersControllerWebSocket;
import com.shouse.restapi.web.domain.NodeParamChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CoreServiceImpl implements CoreService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UsersControllerWebSocket usersControllerWebSocket;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void handleRequestFromCore(NodeParamChangeEvent nodeParamChangeEvent) {
        log.info("UserServiceRest. handleRequestFromCore. Incoming nodeParamChangeEvent: " + nodeParamChangeEvent);
        usersControllerWebSocket.sendMessage(nodeParamChangeEvent);
    }

    @Override
    public void sendRequestToCore(NodeParamChangeEvent nodeParamChangeEvent) {
        String message = processMessage(nodeParamChangeEvent);

        final String uri = "http://localhost:8181";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(message, headers);

        ResponseEntity<String> response = restTemplate.exchange(uri+"/core-rest-api/for-web-application/web-application-event", HttpMethod.POST, entity, String.class);
        log.info("CoreServiceImpl. sendRequestToCore. " +
                "message:" + message +
                "Response: " + response);
    }

    private String processMessage(NodeParamChangeEvent nodeParamChangeEvent){
        RequestToCore requestToCore = new RequestToCore(nodeParamChangeEvent.getNodeId(), nodeParamChangeEvent.getValue());
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonInString = mapper.writeValueAsString(requestToCore);
            return jsonInString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
