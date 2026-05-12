package com.sushi.ecommerce.controller;

import com.sushi.ecommerce.exception.CustomerExistsException;
import com.sushi.ecommerce.exception.CustomerNotFoundException;
import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.service.AuthService;

import java.io.IOException;

public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) throws IOException {
        this.authService = authService;
    }

    public Customer signup(Customer customer) throws CustomerExistsException {
        return authService.signup(customer);
    }

    public Customer login(String email, String password) throws Exception {
        return authService.login(email, password);
    }
    public Customer getCustomerByEmail(String email) throws CustomerNotFoundException {
        return authService.getCustomerByEmail(email);
    }
}
