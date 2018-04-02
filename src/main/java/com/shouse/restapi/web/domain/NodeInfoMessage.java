package com.shouse.restapi.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeInfoMessage {

    private int id;
    private String nodeType;
    private String nodeLocation;
    private NodeInfoMessageControl control;
    private String description;

    public NodeInfoMessage(){}

    public NodeInfoMessage(int id, String nodeType, String nodeLocation, NodeInfoMessageControl control, String description) {
        this.id = id;
        this.nodeType = nodeType;
        this.nodeLocation = nodeLocation;
        this.control = control;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeLocation() {
        return nodeLocation;
    }

    public void setNodeLocation(String nodeLocation) {
        this.nodeLocation = nodeLocation;
    }

    public NodeInfoMessageControl getControl() {
        return control;
    }

    public void setControl(NodeInfoMessageControl control) {
        this.control = control;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NodeInfoMessage{" +
                "id=" + id +
                ", nodeType='" + nodeType + '\'' +
                ", nodeLocation='" + nodeLocation + '\'' +
                ", control=" + control +
                ", description='" + description + '\'' +
                '}';
    }
}
