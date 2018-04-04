package com.shouse.restapi.web.controller;

import com.shouse.restapi.web.domain.WebSocketMessage;
import com.shouse.restapi.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UsersControllerWebSocket implements UsersControllerWebSocketObserver{

    @Autowired
    UserService userService;

    @MessageMapping("/to-server")
    @SendTo("/to-user/messages")
    public WebSocketMessage webSocketMessage(WebSocketMessage webSocketMessage) {
        System.out.println("UsersControllerWebSocket. webSocketMessage");
        return userService.handleWebSocketRequest(webSocketMessage);
    }
}
