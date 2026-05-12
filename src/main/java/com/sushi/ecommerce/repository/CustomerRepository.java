package com.sushi.ecommerce.repository;

import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.model.Product;
import com.sushi.ecommerce.util.CsvReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    public List<Customer> customers;

    public CustomerRepository(CsvReader csvReader) throws IOException {
        this.customers = csvReader.getCustomersFromCsv();
        if (this.customers == null){
            this.customers = new ArrayList<>();
        }
    }

    //CRUD operations
    //1. create
    public Customer save(Customer customer){
        if (customer == null){
            throw new IllegalArgumentException("Customer cant be null");
        }
        customers.add(customer);
        return customer;
    }

    //2. read all customers
    public List<Customer> findALL(){
        return customers;
    }

    //read customers by id
    public Optional<Customer> findById(int id){
        return customers.stream()
                .filter(c-> c.getId() == id)
                .findFirst();
    }
    //3. update
    public Optional<Customer> update(Customer updatedCustomer) {
        Optional<Customer> existing = findById(updatedCustomer.getId());

        existing.ifPresent(c -> {
            Customer updated = Customer.builder()
                    .id(updatedCustomer.getId())
                    .name(updatedCustomer.getName())
                    .email(updatedCustomer.getEmail())
                    .password(updatedCustomer.getPassword())
                    .phoneNo(updatedCustomer.getPhoneNo())
                    .age(updatedCustomer.getAge())
                    .gender(updatedCustomer.getGender())
                    .status(updatedCustomer.getStatus())
                    .membership(updatedCustomer.getMembership())
                    .residentialAddress(updatedCustomer.getResidentialAddress())
                    .shippingAddress(updatedCustomer.getShippingAddress())
                    .lastLoggedIn(updatedCustomer.getLastLoggedIn())
                    .createdOn(c.getCreatedOn()) //preserve createdOn
                    .lastLoggedIn(c.getLastLoggedIn())
                    .build();
        });

        return existing;
    }
    //4. delete
    public void delete(int id){
        customers.removeIf(c -> c.getId() == id);
    }

    public boolean exists(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
    }

    public Optional<Customer> findByEmail(String email) {
        return customers.stream()
                .filter(customer -> customer.getEmail() == email)
                .findFirst();
    }
}
