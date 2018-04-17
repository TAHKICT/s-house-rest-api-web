package com.shouse.restapi.web.service.user;

import com.shouse.restapi.web.domain.NodeParamChangeEvent;
import com.shouse.restapi.web.domain.RequestGetNodes;
import com.shouse.restapi.web.domain.NodeInfoMessage;
import com.shouse.restapi.web.service.core.CoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

public class UserServiceRest implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String coreApiURL = "http://localhost:8181/core-rest-api/for-web-application";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CoreService coreService;

    @Override
    public List<String> getMenuSortingTypes() {
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(coreApiURL + "/menu/get-sort-types", String[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<String> getMenuItems(String sortedBy) {
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(coreApiURL + "/menu/get-items")
                .queryParam("sortedBy", sortedBy);

        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(url.toUriString(), String[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<NodeInfoMessage> getNodes(RequestGetNodes requestGetNodes) {
        ResponseEntity<NodeInfoMessage[]> responseEntity = restTemplate.postForEntity(coreApiURL + "/content/get-nodes", requestGetNodes,NodeInfoMessage[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public NodeParamChangeEvent handleNodeChangeEvent(NodeParamChangeEvent nodeParamChangeEvent) {
        ResponseEntity<NodeParamChangeEvent> responseEntity = restTemplate.postForEntity(coreApiURL + "/content/node-parameter-change", nodeParamChangeEvent, NodeParamChangeEvent.class);
        NodeParamChangeEvent nodeParamChangeEventFromCore = responseEntity.getBody();

        return nodeParamChangeEventFromCore;
    }

    @Override
    public List<NodeInfoMessage> getNodes(String nodeType) {
        ResponseEntity<NodeInfoMessage[]> responseEntity = restTemplate.getForEntity(coreApiURL + "/get-active-nodes/"+nodeType, NodeInfoMessage[].class);
        NodeInfoMessage[] nodeInfoMessages = responseEntity.getBody();
        System.out.println(Arrays.asList(nodeInfoMessages));
        return Arrays.asList(nodeInfoMessages);
    }


}
