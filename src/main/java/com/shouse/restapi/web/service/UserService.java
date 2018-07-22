package com.shouse.restapi.web.service;

import com.shouse.restapi.web.communicators.CommunicatorWithCore;
import com.shouse.restapi.web.controller.UsersControllerWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import shouse.core.common.SystemConstants;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.request.RequestIdGenerator;
import shouse.core.node.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersControllerWebSocket usersControllerWebSocket;

    private CommunicatorWithCore communicatorWithCore;
    private Map <String,Request> requestMap = new HashMap();

    public UserService(CommunicatorWithCore communicatorWithCore) {
        this.communicatorWithCore = communicatorWithCore;

//        usersControllerWebSocket.sendMessage(Map.of("global", "Server stared"));
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

    public void processWebSocketEventFromClient(Map<String,String> webSocketEventParams) {
        LOGGER.info("processWebSocketEventFromClient. webSocketEventParams: " + webSocketEventParams.toString());
        if(areDuplicateParams(webSocketEventParams)){
            LOGGER.info("Web socket event params are duplicated");
            return;
        }

            String requestId = String.valueOf(RequestIdGenerator.generateId());
            Request request = new Request(webSocketEventParams);
            requestMap.put(requestId, request);
            request.addParameter(SystemConstants.requestId, requestId);

            Response response = communicatorWithCore.sendRequest(request);
            LOGGER.info("Receive quick response from core: " + response);

            if(requestMap.get(requestId) != null) {
                Map webSocketResponseParams = new HashMap<String, String>();
                webSocketResponseParams.put("type", "nodesInProcess");
                webSocketResponseParams.put("nodeId", request.getBody().getParameter("nodeId"));
                usersControllerWebSocket.sendMessage(webSocketResponseParams);
            }
    }

    public void processResponseFromCore(Response response){
        LOGGER.info("processResponseFromCore. ".concat(response.toString()));
        if(response.getData().get(SystemConstants.requestId) != null){
            LOGGER.info("Got response by request ID: ".concat(response.getData().get(SystemConstants.requestId).toString()));
            Request request = requestMap.get(response.getData().get(SystemConstants.requestId));

            if(request == null)
                LOGGER.error("request is null");


            if(response.getData().get(SystemConstants.executionStatus).toString().equals("READY")) {
                usersControllerWebSocket.sendMessage(request.getBody().getParameters());
                requestMap.remove(response.getData().get(SystemConstants.requestId));
            }
        }

        if(response.getData().get(SystemConstants.topic) != null && response.getData().get(SystemConstants.topic).equals(SystemConstants.nodeAliveTopic)){
            LOGGER.info("processResponseFromCore. ".concat("Got nod alive message from core: ").concat(response.getData().toString()));
            Map<String,String> webSocketParams = new HashMap<>();
            webSocketParams.put(SystemConstants.topic, SystemConstants.nodeAliveTopic);
            webSocketParams.put(SystemConstants.nodeAliveState, Boolean.toString((Boolean) response.getData().get(SystemConstants.nodeAliveState)));
            usersControllerWebSocket.sendMessage(webSocketParams);
        }
    }

    public List<String> getInProcessNodesIdList (){
            return requestMap.values().stream()
                    .filter(val -> val.getBody().getParameter("nodeId") != null)
                    .map(val -> val.getBody().getParameter("nodeId"))
                    .collect(Collectors.toList());
    }

    private boolean areDuplicateParams(Map<String,String> map){
        return requestMap.values().stream().map(request -> request.getBody().getParameters()).anyMatch(params -> params.equals(map));
    }
}
