package com.sbrf.reboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Response implements Serializable {
    private String statusCode;

    public Response(@JsonProperty("statusCode") String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}