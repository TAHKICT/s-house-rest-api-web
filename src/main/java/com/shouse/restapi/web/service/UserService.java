package com.shouse.restapi.web.service;

import com.shouse.restapi.web.domain.NodeInfoMessage;

import java.util.List;

public interface UserService {

    public List<NodeInfoMessage> getActiveNodes(String nodeType);

}
