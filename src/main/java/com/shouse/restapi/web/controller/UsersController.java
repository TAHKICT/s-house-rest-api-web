package com.shouse.restapi.web.controller;

import com.shouse.restapi.web.domain.RequestGetNodes;
import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/web-rest-api/user/admin-ui")
public class UsersController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @RequestMapping("/menu/get-sorting-types")
    public List<String> getMenuSortingTypes(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        log.info("UsersController. getMenuSortingTypes");

        return userService.getMenuSortingTypes();
    }

    @RequestMapping("/menu/get-items")
    public List<String> getMenuItems(@RequestParam(value = "menuItemsSortedBy") String menuItemsSortedBy, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        log.info("UsersController. getMenuSortingTypes");

        return userService.getMenuItems(menuItemsSortedBy);
    }

    @RequestMapping("/content/get-nodes/{nodeType}")
    public List<NodeInfoMessage> getActiveNodesByType(@PathVariable(value="nodeType") String nodeType,
                                                      HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        log.info("UsersController. getActiveNodesByType: " + nodeType);

        return userService.getActiveNodes(nodeType);
    }

    @RequestMapping("/get-active-nodes")
    public List<NodeInfoMessage> getActiveNodes(@RequestBody RequestGetNodes message,
                                                      HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        log.info("UsersController. getActiveNodesByType: " + message);

        return userService.getActiveNodes(message);
    }



}
