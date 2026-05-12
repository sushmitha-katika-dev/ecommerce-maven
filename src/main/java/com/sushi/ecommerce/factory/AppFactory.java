package com.sushi.ecommerce.factory;

import com.sushi.ecommerce.controller.CustomerController;
import com.sushi.ecommerce.controller.ProductController;
import com.sushi.ecommerce.repository.CustomerRepository;
import com.sushi.ecommerce.repository.ProductRepository;
import com.sushi.ecommerce.service.CustomerService;
import com.sushi.ecommerce.service.CustomerServiceImpl;
import com.sushi.ecommerce.service.ProductService;
import com.sushi.ecommerce.service.ProductServiceImpl;
import com.sushi.ecommerce.ui.CustomerUI;
import com.sushi.ecommerce.ui.ProductUI;
import com.sushi.ecommerce.util.CsvReader;

import java.io.IOException;

public class AppFactory {

    // =========================
    // CORE SINGLETON
    // =========================
    private static CsvReader csvReader;

    // =========================
    // CSV READER
    // =========================
    public static CsvReader getCsvReader() {
        if(csvReader == null) {
            csvReader = new CsvReader();
        }
        return csvReader;
    }

    // =========================
    // PRODUCT FLOW
    //CsvReader → ProductRepository → ProductService → ProductController
    // =========================
    private static ProductRepository productRepository;
    private static ProductService productService;
    private static ProductController productController;
    private  static ProductUI productUI;

    // =========================
    // CUSTOMER FLOW
    //CsvReader → CustomerRepository → CustomerService → CustomerController
    // =========================
    private static CustomerRepository customerRepository;
    private static CustomerService customerService;
    private static CustomerController customerController;
    private  static CustomerUI customerUI;

    // =========================
    // PRODUCT DEPENDENCIES
    // =========================

    public static ProductRepository getProductRepository() throws IOException {
        if (productRepository == null) {
            productRepository = new ProductRepository(getCsvReader());
        }
        return productRepository;
    }

    public static ProductService getProductService() throws IOException {
        if (productService == null) {
            productService = new ProductServiceImpl(getProductRepository());
        }
        return productService;
    }

    public static ProductController getProductController() throws IOException {
        if (productController == null) {
            productController = new ProductController(getProductService());
        }
        return productController;
    }

    // =========================
    // CUSTOMER DEPENDENCIES
    // =========================

    public static CustomerRepository getCustomerRepository() throws IOException {
        if (customerRepository == null) {
            customerRepository = new CustomerRepository(getCsvReader());
        }
        return customerRepository;
    }

    public static CustomerService getCustomerService() throws IOException {
        if (customerService == null) {
            customerService = new CustomerServiceImpl(getCustomerRepository());
        }
        return customerService;
    }

    public static CustomerController getCustomerController() throws IOException {
        if(customerController == null) {
            customerController = new CustomerController(getCustomerService());
        }
        return customerController;
    }


    public static ProductUI getProductUI() {
        if (productUI == null) {
            productUI = new ProductUI();
        }
        return productUI;
    }
    public static CustomerUI getCustomerUI() {
        if (customerUI == null) {
            customerUI = new CustomerUI();
        }
        return customerUI;
    }
}
