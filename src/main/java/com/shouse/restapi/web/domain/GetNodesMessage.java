package com.shouse.restapi.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetNodesMessage {

    private String type;
    private String name;

    public GetNodesMessage() {}

    public GetNodesMessage(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetNodesMessage{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
