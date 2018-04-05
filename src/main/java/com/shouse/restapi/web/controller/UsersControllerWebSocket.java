package com.shouse.restapi.web.controller;

import com.shouse.restapi.web.domain.WebSocketMessage;
import com.shouse.restapi.web.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UsersControllerWebSocket{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    SimpMessagingTemplate restTemplate;

    @MessageMapping("/to-server")
    @SendTo("/to-user/messages")
    public WebSocketMessage webSocketMessage(WebSocketMessage webSocketMessage) {
        log.info("UsersControllerWebSocket. webSocketMessage. : " + webSocketMessage);
        return userService.handleWebSocketRequest(webSocketMessage);
    }

    public void sendMessage(WebSocketMessage webSocketMessage) {
        log.info("UsersControllerWebSocket. sendMessage. " + webSocketMessage);
        this.restTemplate.convertAndSend("/to-user/messages", webSocketMessage);
    }
}
