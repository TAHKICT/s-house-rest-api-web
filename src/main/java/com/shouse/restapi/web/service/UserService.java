package com.shouse.restapi.web.service;

import com.shouse.restapi.web.communicators.CommunicatorWithCore;
import com.shouse.restapi.web.controller.UsersControllerWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import shouse.core.common.SystemConstants;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private CommunicatorWithCore communicatorWithCore;
    private Map <String,Request> requestMap = new HashMap();

    @Autowired
    UsersControllerWebSocket usersControllerWebSocket;

    public UserService(CommunicatorWithCore communicatorWithCore) {
        this.communicatorWithCore = communicatorWithCore;
    }

    public List<NodeInfo> getNodes(boolean isActiveOnly){
        Request request = new Request("type", "general");
        if(isActiveOnly)
            request.addParameter("command", "activeNodes");
        else
            request.addParameter("command", "allNodes");

        Response response = communicatorWithCore.sendRequest(request);
        List<NodeInfo> nodeInfoList = (List<NodeInfo>) response.getData().get("nodes");

        return nodeInfoList;
    }

    public void handleNodeChangeEvent(Map<String,String> nodeEventMessage) {
        Request request = new Request(nodeEventMessage);
        Response response = communicatorWithCore.sendRequest(request);
        LOGGER.info("Receive quick response from core: " + response);
        requestMap.put(response.getData().get(SystemConstants.requestId).toString(), request);
    }

    public void processResponseFromCore(Response response){
        LOGGER.info("processResponseFromCore. ".concat(response.toString()));
        if(response.getData().get(SystemConstants.requestId) != null){
            LOGGER.info("Got response by request ID: ".concat(response.getData().get(SystemConstants.requestId).toString()));
            Request request = requestMap.get(response.getData().get(SystemConstants.requestId));

            if(request == null)
                LOGGER.error("request is null");


            if(response.getData().get(SystemConstants.executionStatus).toString().equals("READY"))
                usersControllerWebSocket.sendMessage(request.getBody().getParameters());
        }
    }
}
