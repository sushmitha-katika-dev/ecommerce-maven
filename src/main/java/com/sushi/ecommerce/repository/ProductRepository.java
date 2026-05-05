package com.sushi.ecommerce.repository;

import com.sushi.ecommerce.model.Product;
import com.sushi.ecommerce.util.CsvReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    private List<Product> products;

    public ProductRepository(CsvReader csvReader) throws IOException {
        this.products = csvReader.getProductsFromCsv();

        if (this.products == null) {
            this.products = new ArrayList<>();
        }
    }

    //CRUP OPERATIONS
    //1. create
    public Product save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
        return product;
    }
    //2. read all products
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }
    // read products by id
    public Optional<Product> findById(int id) {
        return products.stream()
                            .filter(product -> product.getId() == id)
                            .findFirst();
    }
    //3. update
    public Product update(int id, Product product) {
        products.replaceAll(p -> p.getId() == id ? product : p);
        return product;
    }
    //4. delete by id
    public void delete(int id){
        products.removeIf(product -> product.getId() == id);
    }
    // delete by product
    public void delete(Product product){
        products.remove(product);
    }
}
