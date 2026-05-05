package com.sushi.ecommerce.controller;

import com.sushi.ecommerce.exception.ProductExistsException;
import com.sushi.ecommerce.exception.ProductNotFoundException;
import com.sushi.ecommerce.model.Product;
import com.sushi.ecommerce.service.ProductService;

import java.util.List;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*
    CRUD OPERATIONS
     */
    public Product save(Product product) throws ProductExistsException {
        return productService.save(product);
    }

    public Product getById(int id) throws ProductNotFoundException {
        return productService.getById(id);
    }

    public List<Product> getAll() {
        return productService.getAll();
    }

    public Product update(int id, Product product) throws ProductNotFoundException{
        return productService.update(id, product);
    }

    public void delete(int id) throws ProductNotFoundException{
        productService.delete(id);
    }
}
