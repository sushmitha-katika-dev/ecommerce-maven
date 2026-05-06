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
    public Customer update(int id, Customer customer) {
        customers.replaceAll(c -> c.getId() == id ? customer : c);
        return customer;
    }
    //4. delete
    public void delete(int id){
        customers.removeIf(c -> c.getId() == id);
    }

}
