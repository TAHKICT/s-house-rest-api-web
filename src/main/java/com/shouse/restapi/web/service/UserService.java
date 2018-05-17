package com.shouse.restapi.web.service;

import com.shouse.restapi.web.communicators.CommunicatorWithCore;
import com.shouse.restapi.web.domain.NodeInfoMessageControl;
import com.shouse.restapi.web.domain.RequestGetNodes;
import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.domain.NodeParamChangeEvent;
import shouse.core.Common.NodeType;
import shouse.core.node.NodeInfo;
import shouse.core.node.request.Request;
import shouse.core.node.request.RequestBody;
import shouse.core.node.response.Response;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    CommunicatorWithCore communicatorWithCore;

    public UserService(CommunicatorWithCore communicatorWithCore) {
        this.communicatorWithCore = communicatorWithCore;
    }

    public List<NodeInfoMessage> getNodes(boolean isActiveOnly){
        Request request;
        if(isActiveOnly)
            request = new Request("command", "activeNodes");
        else
            request = new Request("command", "allNodes");

        Response response = communicatorWithCore.sendRequest(request);
        List<NodeInfo> nodeInfoList = (List<NodeInfo>) response.getData().get("nodes");

        return nodeInfoList.stream().map(nodeInfo -> {
            NodeInfoMessage nodeInfoMessage = new NodeInfoMessage();
            nodeInfoMessage.setId(nodeInfo.getId());
            nodeInfoMessage.setNodeType(NodeType.getNodeTypeById(nodeInfo.getNodeTypeId()).getName());
            nodeInfoMessage.setNodeLocation("arrrarara");
            nodeInfoMessage.setControl(new NodeInfoMessageControl("checkbox","checked"));
            return nodeInfoMessage;
        }).collect(Collectors.toList());
    }

}
