package com.sushi.ecommerce.service;

import com.sushi.ecommerce.exception.CustomerExistsException;
import com.sushi.ecommerce.exception.CustomerNotFoundException;
import com.sushi.ecommerce.exception.InvalidCredentialsException;
import com.sushi.ecommerce.model.Customer;

import java.time.LocalDateTime;

public class AuthServiceImpl implements AuthService{

    private final CustomerService customerService;

    public AuthServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Customer signup(Customer customer) throws CustomerExistsException {
        if (customerService.exists(customer.getEmail())) {
            throw new CustomerExistsException(
                    "Customer already exists with email: " + customer.getEmail());
        }
    return customerService.save(customer);
    }

    @Override
    public Customer login(String email, String password) throws InvalidCredentialsException {

        Customer customer = customerService.getByEmail(email);
        if(customer == null){
            throw new InvalidCredentialsException("Invalid email or password");
        }
        if (!customer.getPassword().equals(password))
            throw new IllegalArgumentException("Invalid email or password");
        // update last login
        customer.setLastLoggedIn(LocalDateTime.now());
        return customer;
    }

    @Override
    public Customer getCustomerByEmail(String email) throws CustomerNotFoundException {
        return customerService.getByEmail(email);
    }
}
