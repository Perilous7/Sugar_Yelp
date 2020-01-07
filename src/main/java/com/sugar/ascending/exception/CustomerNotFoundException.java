package com.sugar.ascending.exception;

import com.sugar.ascending.model.Customer;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class CustomerNotFoundException extends AppBaseException {
    private Customer user;

    public CustomerNotFoundException() {
        super(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), "The Customer is not found, please check if the user's email or password is correct.");
    }

    public CustomerNotFoundException(String message) {
        this();
        if (message != null) this.message = message;
    }

    public CustomerNotFoundException(Customer user) {
        this();
        if (user != null) this.user = user;
    }

    public CustomerNotFoundException(String message, Customer user) {
        this();
        if (message != null) this.message = message;
        if (user != null) this.user = user;
    }

    @Override
    public Map<String, Object> getFormattedMessage() {
        Map<String, Object> map = super.getFormattedMessage();
        if (user != null) map.put("user", user);
        return map;
    }
}
