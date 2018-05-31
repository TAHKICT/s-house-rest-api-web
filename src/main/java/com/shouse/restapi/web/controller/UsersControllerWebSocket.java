package com.shouse.restapi.web.controller;

import com.shouse.restapi.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;

@CrossOrigin
@Controller
public class UsersControllerWebSocket{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    SimpMessagingTemplate restTemplate;

//    @MessageMapping("/to-server")
//    @SendTo("/to-user/messages")
//    public NodeEventMessage webSocketMessage(NodeEventMessage nodeParamChangeEvent) {
//        LOGGER.info("UsersControllerWebSocket. nodeParamChangeEvent. : " + nodeParamChangeEvent);
//        return userService.processNodeChangeEventFromClient(nodeParamChangeEvent);
//    }

    @MessageMapping("/to-server")
    @SendTo("/to-user/messages")
    public void webSocketMessage(Map<String,String> eventParams) {
        LOGGER.info("UsersControllerWebSocket. nodeParamChangeEvent. : " + eventParams);
        userService.processNodeChangeEventFromClient(eventParams);
    }

    public void sendMessage(Map<String,String> eventParams) {
        LOGGER.info("UsersControllerWebSocket. sendMessage. " + eventParams);
        this.restTemplate.convertAndSend("/to-user/messages", eventParams);
    }
}
