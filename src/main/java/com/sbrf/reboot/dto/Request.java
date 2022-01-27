package com.sbrf.reboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Request implements Serializable {
    private String atmNumber;

    public Request(@JsonProperty("atmNumber") String atmNumber) {
        this.atmNumber = atmNumber;
    }

    public String getAtmNumber() {
        return atmNumber;
    }
}