package com.shouse.restapi.web.controller;

import com.shouse.restapi.web.domain.NodeParamChangeEvent;
import com.shouse.restapi.web.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Controller
public class UsersControllerWebSocket{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    SimpMessagingTemplate restTemplate;

    @MessageMapping("/to-server")
    @SendTo("/to-user/messages")
    public NodeParamChangeEvent webSocketMessage(NodeParamChangeEvent nodeParamChangeEvent) {
        log.info("UsersControllerWebSocket. nodeParamChangeEvent. : " + nodeParamChangeEvent);
        return userService.handleNodeChangeEvent(nodeParamChangeEvent);
    }

    public void sendMessage(NodeParamChangeEvent nodeParamChangeEvent) {
        log.info("UsersControllerWebSocket. sendMessage. " + nodeParamChangeEvent);
        this.restTemplate.convertAndSend("/to-user/messages", nodeParamChangeEvent);
    }
}
