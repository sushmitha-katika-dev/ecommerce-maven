package com.sushi.ecommerce.service;

import com.sushi.ecommerce.exception.CustomerExistsException;
import com.sushi.ecommerce.exception.CustomerNotFoundException;
import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.repository.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        customerRepository.findById(customer.getId())
                .ifPresent(c -> {
                    throw new CustomerExistsException("Customer already existswith id: " + customer.getId());
                });
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findALL();
    }

    @Override
    public Customer getById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.update(customer)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found with name : " + customer.getName()));
    }

    @Override
    public void delete(int id) {
        customerRepository.findById(id)
                 .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(id);
    }

    @Override
    public boolean exists(String email) throws CustomerNotFoundException {
        return this.customerRepository.exists(email);
    }

    @Override
    public Customer getByEmail(String email) throws CustomerNotFoundException {
        return this.customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + email));
    }
}
