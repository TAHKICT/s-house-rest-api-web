package com.shouse.restapi.web.controller;

import com.shouse.restapi.web.domain.WebSocketMessage;
import com.shouse.restapi.web.service.core.CoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CoreController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CoreService coreService;

    @RequestMapping("/web-rest-api/for-core-application")
    public String handleRequestFromCore(@RequestBody WebSocketMessage message, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");

        log.info("CoreController. handleRequestFromCore. Incoming message:  " + message);

        coreService.handleRequestFromCore(message);
        return "OK";
    }
}
