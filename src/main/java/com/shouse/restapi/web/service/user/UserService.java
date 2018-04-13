package com.shouse.restapi.web.service.user;

import com.shouse.restapi.web.domain.RequestGetNodes;
import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.domain.WebSocketMessage;

import java.util.List;

public interface UserService {

    public List<String> getMenuItems(String menuItemsSortedBy);

    public List<NodeInfoMessage> getActiveNodes(String nodeType);

    public List<NodeInfoMessage> getActiveNodes(RequestGetNodes requestGetNodes);

    public WebSocketMessage handleWebSocketRequest(WebSocketMessage webSocketMessage);

    public List<String> getMenuSortingTypes();
}
