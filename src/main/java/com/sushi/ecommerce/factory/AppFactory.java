package com.sushi.ecommerce.factory;

import com.sushi.ecommerce.controller.AuthController;
import com.sushi.ecommerce.controller.CustomerController;
import com.sushi.ecommerce.controller.ProductController;
import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.repository.CustomerRepository;
import com.sushi.ecommerce.repository.ProductRepository;
import com.sushi.ecommerce.service.*;
import com.sushi.ecommerce.ui.AuthUI;
import com.sushi.ecommerce.ui.CustomerUI;
import com.sushi.ecommerce.ui.DashboardUI;
import com.sushi.ecommerce.ui.ProductUI;
import com.sushi.ecommerce.util.CsvReader;

import java.io.IOException;

public class AppFactory {

    // =========================
    // CORE SINGLETON
    // =========================
    private static CsvReader csvReader;

    // =========================
    // AUTH FLOW
    // =========================

    private static AuthController authController;
    private static AuthService authService;
    private static AuthUI authUI;

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
    private static DashboardUI dashboardUI;


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

    // =========================
    // AUTH DEPENDENCIES
    // =========================

    public static AuthService getAuthService() throws IOException {
        if (authService == null) {
            authService = new AuthServiceImpl(getCustomerService());
        }
        return authService;
    }


    public static AuthController getAuthController() throws IOException {
        if (authController == null) {
            authController = new AuthController(getAuthService());
        }
        return authController;
    }


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

    public static ProductUI getProductUI() {
        if (productUI == null) {
            productUI = new ProductUI();
        }
        return productUI;
    }
    public static AuthUI getAuthUI() throws IOException {
        if(authUI == null)
            authUI = new AuthUI(getAuthController());
        return authUI;
    }

    public static CustomerUI getCustomerUI(Customer customer) {
        if(customerUI == null)
            customerUI = new CustomerUI(customer);
        return customerUI;
    }

    public static DashboardUI getDashboardUI(Customer customer) {
        if(dashboardUI == null)
            dashboardUI = new DashboardUI(customer);
        return dashboardUI;
    }
}
