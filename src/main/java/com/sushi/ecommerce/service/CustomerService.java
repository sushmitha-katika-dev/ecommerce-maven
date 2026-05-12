package com.sushi.ecommerce.service;

import com.sushi.ecommerce.exception.CustomerExistsException;
import com.sushi.ecommerce.exception.CustomerNotFoundException;
import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.model.Product;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer) throws CustomerExistsException;
    List<Customer> getAll() throws CustomerNotFoundException;
    Customer getById(int id) throws CustomerNotFoundException;
    Customer update(Customer customer) throws CustomerNotFoundException;
    void delete(int id) throws CustomerNotFoundException;
    boolean exists(String email) throws CustomerNotFoundException;

    Customer getByEmail(String email) throws CustomerNotFoundException;
}
