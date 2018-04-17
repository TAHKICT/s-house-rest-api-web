package com.shouse.restapi.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestGetNodes {

    private String type;
    private String name;
    private boolean activeOnly;

    public RequestGetNodes() {}

    public RequestGetNodes(String type, String name, boolean activeOnly) {
        this.type = type;
        this.name = name;
        this.activeOnly = activeOnly;
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

    public boolean isActiveOnly() {
        return activeOnly;
    }

    public void setActiveOnly(boolean activeOnly) {
        this.activeOnly = activeOnly;
    }

    @Override
    public String toString() {
        return "RequestGetNodes{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
