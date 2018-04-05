package com.shouse.restapi.web.service.core;

public class RequestToCore {

    private int nodeId;
    private String value;

    public RequestToCore() {
    }

    public RequestToCore(int nodeId, String value) {
        this.nodeId = nodeId;
        this.value = value;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
