package com.shouse.restapi.web.controller;

import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UsersController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @RequestMapping("/user/getactivenodes/{nodeType}")
    public List<NodeInfoMessage> getActiveNodesByType(@PathVariable(value="nodeType") String nodeType,
                                                      HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        log.info("UsersController. getActiveNodesByType: " + nodeType);

        return userService.getActiveNodes(nodeType);
    }

}
