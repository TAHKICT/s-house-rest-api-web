package com.shouse.restapi.web.domain;

public class NodeParamChangeEvent {
    private int nodeId;
    private String value;

    public NodeParamChangeEvent() {}

    public NodeParamChangeEvent(int nodeId, String value) {
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

    @Override
    public String toString() {
        return "NodeParamChangeEvent{" +
                "nodeId=" + nodeId +
                ", value='" + value + '\'' +
                '}';
    }
}
