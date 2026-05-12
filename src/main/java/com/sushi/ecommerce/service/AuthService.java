package com.sushi.ecommerce.service;

import com.sushi.ecommerce.exception.CustomerExistsException;
import com.sushi.ecommerce.exception.CustomerNotFoundException;
import com.sushi.ecommerce.exception.InvalidCredentialsException;
import com.sushi.ecommerce.model.Customer;

public interface AuthService {

    Customer signup(Customer customer) throws CustomerExistsException;

    Customer login(String email, String password) throws InvalidCredentialsException;

    Customer getCustomerByEmail(String email) throws CustomerNotFoundException;
}

