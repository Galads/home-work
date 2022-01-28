package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class JSONUtils {
    public static <T> String toJSON(T typeOfReaction) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(typeOfReaction);
    }

    public static Request JSONtoRequest(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, Request.class);
    }

    public static Response JSONtoResponse(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, Response.class);
    }
}
