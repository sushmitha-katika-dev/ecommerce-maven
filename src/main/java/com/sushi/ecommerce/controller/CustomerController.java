package com.sushi.ecommerce.controller;

import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.service.CustomerService;

import java.util.List;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
    CRUD OPERATIONS
    */

    public Customer save(Customer customer) {
        return customerService.save(customer);
    }
    public Customer getById(int id){
        return customerService.getById(id);
    }
    public List<Customer> getAll(){
        return customerService.getAll();
    }
    public Customer update(Customer customer) {
        return customerService.update(customer);
    }
    public void delete(int id) {
        customerService.delete(id);
    }
}
