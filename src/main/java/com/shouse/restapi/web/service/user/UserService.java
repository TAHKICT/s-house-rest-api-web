package com.shouse.restapi.web.service.user;

import com.shouse.restapi.web.domain.RequestGetNodes;
import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.domain.NodeParamChangeEvent;

import java.util.List;

public interface UserService {

    public List<String> getMenuItems(String menuItemsSortedBy);

    public List<NodeInfoMessage> getNodes(String nodeType);

    public List<NodeInfoMessage> getNodes(RequestGetNodes requestGetNodes);

    public NodeParamChangeEvent handleNodeChangeEvent(NodeParamChangeEvent nodeParamChangeEvent);

    public List<String> getMenuSortingTypes();
}
