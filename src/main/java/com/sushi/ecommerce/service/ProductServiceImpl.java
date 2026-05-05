package com.sushi.ecommerce.service;

import com.sushi.ecommerce.exception.ProductExistsException;
import com.sushi.ecommerce.exception.ProductNotFoundException;
import com.sushi.ecommerce.model.Product;
import com.sushi.ecommerce.repository.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        productRepository.findById(product.getId())
                .ifPresent(p -> {
                    throw new ProductExistsException("Product already exists with id: "+product.getId());
                });
        return productRepository.save(product);
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product not found with id: "+id));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(int id, Product product) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productRepository.update(id,product);
    }

    @Override
    public void delete(int id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(id);
    }
}
