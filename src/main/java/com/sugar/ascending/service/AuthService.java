package com.sugar.ascending.service;

import com.sugar.ascending.model.Customer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AuthService {
    int authorize(HttpServletRequest req);
    Map<String, String> authenticate(Customer user);

}
