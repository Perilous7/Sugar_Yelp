package com.sugar.ascending.controller;

import com.sugar.ascending.model.Customer;
import com.sugar.ascending.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = {"/auth"})
public class AuthenticationController {
    private AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity authenticate(@RequestBody Customer user) {
        Map<String, String> token = authService.authenticate(user);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity authenticate(@RequestParam String email, @RequestParam String password) {
        Customer user = new Customer();
        user.setEmail(email);
        user.setPassword(password);
        return authenticate(user);
    }
}
