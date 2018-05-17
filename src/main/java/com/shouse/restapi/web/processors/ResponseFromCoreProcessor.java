package com.shouse.restapi.web.processors;

import com.shouse.restapi.web.communicators.CommunicatorWithCore;
import com.shouse.restapi.web.controller.UsersControllerWebSocket;
import com.shouse.restapi.web.domain.NodeParamChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import shouse.core.node.response.Response;

public class ResponseFromCoreProcessor implements Runnable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersControllerWebSocket usersControllerWebSocket;

    private CommunicatorWithCore communicator;
    private boolean running;

    public ResponseFromCoreProcessor(CommunicatorWithCore communicator) {
        this.communicator = communicator;
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
        NodeParamChangeEvent nodeParamChangeEvent = new NodeParamChangeEvent();
        usersControllerWebSocket.sendMessage(nodeParamChangeEvent);
    }
}
