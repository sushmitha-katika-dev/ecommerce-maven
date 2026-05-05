package com.sushi.ecommerce.service;

import com.sushi.ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);
    Product getById(int id);
    List<Product> getAll();
    Product update(int id, Product product);
    void delete(int id);
}
