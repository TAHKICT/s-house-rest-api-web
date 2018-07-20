package com.shouse.restapi.web.controller;

import com.shouse.restapi.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shouse.core.node.NodeInfo;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/web-rest-api/user/admin-ui")
public class UsersController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @RequestMapping("/content/get-nodes")
    public List<NodeInfo> getNodesTest() {
        log.info("getNodesTest");
        return userService.getNodes(false);
    }

    @RequestMapping("/content/get-in-process-nodes-id-list")
    public List<String> getInProcessNodesIdList() {
        log.info("getInProcessNodesIdList");
        return userService.getInProcessNodesIdList();
    }

}
