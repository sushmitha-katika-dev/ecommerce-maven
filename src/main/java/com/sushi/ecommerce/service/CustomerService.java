package com.sushi.ecommerce.service;

import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.model.Product;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);
    List<Customer> getAll();
    Customer getById(int id);
    Customer update(Customer customer);
    void delete(int id);
}
