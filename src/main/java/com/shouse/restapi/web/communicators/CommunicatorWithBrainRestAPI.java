package com.shouse.restapi.web.communicators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import shouse.core.node.request.Request;
import shouse.core.node.response.Response;

@CrossOrigin
@RestController
public class CommunicatorWithBrainRestAPI implements CommunicatorWithBrain {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String coreApplicationEntryPointURL = "http://localhost:8181/core-rest-api/for-web-application/entry-point";
    private RestTemplate restTemplate;
    private Response response;
    private boolean hasResponse;

    @Autowired
    public CommunicatorWithBrainRestAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Response sendRequest(Request request) {
        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(coreApplicationEntryPointURL, request, Response.class);
        return responseEntity.getBody();
    }

    @Override
    public Response receiveResponse() {
        hasResponse = false;
        return response;
    }

    @Override
    public boolean hasResponse() {
        return hasResponse;
    }

    @RequestMapping("/web-rest-api/for-core-application/entry-point")
    public void entryPoint(@RequestBody Response response) {
        log.info("entryPoint. response: ".concat(response.toString()));
        this.response = response;
        this.hasResponse = true;
    }
}
