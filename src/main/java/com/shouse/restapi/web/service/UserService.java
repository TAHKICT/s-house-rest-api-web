package com.shouse.restapi.web.service;

import com.shouse.restapi.web.communicators.CommunicatorWithCore;
import com.shouse.restapi.web.domain.NodeParamChangeEvent;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.response.Response;

import java.util.List;

public class UserService {

    private CommunicatorWithCore communicatorWithCore;

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

    public NodeParamChangeEvent handleNodeChangeEvent(NodeParamChangeEvent nodeParamChangeEvent) {
        return new NodeParamChangeEvent();
    }
}
