package com.shouse.restapi.web.communicators;

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
public class CommunicatorWithCoreRestAPI implements CommunicatorWithCore{

    private String coreApplicationEntryPointURL = "http://localhost:8181/core-rest-api/for-web-application/entry-point";
    private RestTemplate restTemplate;
    private Response response;
    private boolean hasResponse;

    @Autowired
    public CommunicatorWithCoreRestAPI(RestTemplate restTemplate) {
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
    public void entryPoint (@RequestBody Response response) {
        this.response = response;
        this.hasResponse = true;
    }
}
