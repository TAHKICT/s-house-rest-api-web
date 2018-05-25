package com.shouse.restapi.web.service;

import com.shouse.restapi.web.communicators.CommunicatorWithCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Map <String,Response> responseMap = new HashMap();


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
        Response response = communicatorWithCore.sendRequest(new Request(nodeEventMessage));
        LOGGER.info("Receive quick response from core: " + response);
        responseMap.put(response.getData().get(SystemConstants.requestId).toString(), response);
    }

    public void processResponseFromCore(Response response){
        if(responseMap.get(response.getData().get(SystemConstants.requestId).toString()) != null){
            LOGGER.info("Got response by request ID: ".concat(response.toString()));

        }
    }
}
