package com.sushi.ecommerce.factory;

import com.sushi.ecommerce.controller.ProductController;
import com.sushi.ecommerce.repository.ProductRepository;
import com.sushi.ecommerce.service.ProductService;
import com.sushi.ecommerce.service.ProductServiceImpl;
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
    private  static ProductUI ProductMenuUI;

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
}
