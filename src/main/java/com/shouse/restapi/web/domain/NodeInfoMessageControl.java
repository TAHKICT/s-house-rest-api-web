package com.shouse.restapi.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeInfoMessageControl {
    private String type;
    private String value;

    public NodeInfoMessageControl(){}

    public NodeInfoMessageControl(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NodeInfoMessageControl{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
