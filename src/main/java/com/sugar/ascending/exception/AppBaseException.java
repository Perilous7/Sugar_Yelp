package com.sugar.ascending.exception;

import java.util.LinkedHashMap;
import java.util.Map;

public class AppBaseException extends RuntimeException {
    protected String message;
    protected int statusCode;

    public AppBaseException() {}

    public AppBaseException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, Object> getFormattedMessage() {
        Map<String, Object> map = new LinkedHashMap();
        map.put("status code", statusCode);
        map.put("message", message);
        return map;
    }
}
