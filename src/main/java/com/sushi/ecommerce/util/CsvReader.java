package com.sushi.ecommerce.util;

import com.sushi.ecommerce.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<Product> getProductsFromCsv() throws IOException{

        List<Product> products = new ArrayList<>();

        File file = new File("C:/java-workspace/products.cvs");

        if (!file.exists()){
            return products;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String productsData;
            while ((productsData = br.readLine()) != null){

                String[] data = productsData.split(",");

                Product product = Product.builder()
                        .id(Integer.parseInt(data[0]))
                        .name(data[1])
                        .maxRetailPrice(Double.parseDouble(data[2]))
                        .discountPercentage(Float.parseFloat(data[3]))
                        .rating(Integer.parseInt(data[4]))
                        .isAvailable(Boolean.parseBoolean(data[5]))
                        .company(data[6])
                        .category(data[7])
                        .manufacturedYear(Integer.parseInt(data[8]))
                        .build();

                products.add(product);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
