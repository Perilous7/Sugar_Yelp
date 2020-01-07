package com.sugar.ascending.exception;

import com.sugar.ascending.model.Customer;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class AuthenticationException extends AppBaseException {
    private Customer user;

    public AuthenticationException() {
        super(HttpStatus.BAD_REQUEST.value(), "Bad request.");
    }

    public AuthenticationException(String message) {
        this();
        if (message != null) this.message = message;
    }

    public AuthenticationException(String message, Customer user) {
        this(message);
        this.user = user;
    }

    @Override
    public Map<String, Object> getFormattedMessage() {
        Map<String, Object> map = super.getFormattedMessage();
        if (user != null) map.put("user", user);
        return map;
    }
}
