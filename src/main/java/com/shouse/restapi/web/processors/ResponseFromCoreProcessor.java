package com.shouse.restapi.web.processors;

import com.shouse.restapi.web.communicators.CommunicatorWithBrain;
import com.shouse.restapi.web.controller.UsersControllerWebSocket;
import com.shouse.restapi.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import shouse.core.node.response.Response;

public class ResponseFromCoreProcessor implements Runnable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersControllerWebSocket usersControllerWebSocket;

    private CommunicatorWithBrain communicator;
    private UserService userService;
    private boolean running;

    public ResponseFromCoreProcessor(CommunicatorWithBrain communicator, UserService userService) {
        this.communicator = communicator;
        this.userService = userService;
    }

    public void stop(){
        running = false;
    }

    @Override
    public void run() {
        running = true;
        while (running){
            if(communicator.hasResponse()){
                log.info("Received a response.");
                process(communicator.receiveResponse());
            }
        }
    }

    private void process (Response response){
        userService.processResponseFromCore(response);
    }
}
