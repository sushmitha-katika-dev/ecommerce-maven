package com.sushi.ecommerce.util;

import com.sushi.ecommerce.enums.Gender;
import com.sushi.ecommerce.enums.Membership;
import com.sushi.ecommerce.enums.Status;
import com.sushi.ecommerce.model.Address;
import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.model.Product;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<Product> getProductsFromCsv() throws IOException{

        List<Product> products = new ArrayList<>();

        File file = new File("C:/java-workspace/products.csv");

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
                        .discountPercentage(Double.parseDouble(data[3]))
                        .isAvailable(Boolean.parseBoolean(data[4]))
                        .company(data[5])
                        .category(data[6])
                        .manufacturedYear(Integer.parseInt(data[7]))
                        .build();

                products.add(product);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Customer> getCustomersFromCsv() throws IOException{

        List<Customer> customers = new ArrayList<>();

        File file = new File("C:/java-workspace/customers.csv");

        if (!file.exists()){
            return customers;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            br.readLine(); // skip header

            String customersData;
            while ((customersData = br.readLine()) != null){

                String[] data = customersData.split(",");

                Address residentialAddress = parseAddress(data[11]);
                Address shippingAddress = parseAddress(data[12]);

                Customer customer = Customer.builder()
                        .id(Integer.parseInt(data[0]))
                        .name(data[1])
                        .email(data[2])
                        .password(data[3])
                        .phoneNo(data[4])
                        .age(Byte.parseByte(data[5]))
                        .gender(Gender.valueOf(data[6]))
                        .status(Status.valueOf(data[7]))
                        .membership(Membership.valueOf(data[8]))
                        .createdOn(LocalDateTime.parse(data[9]))
                        .lastLoggedIn(LocalDateTime.parse(data[10]))
                        .residentialAddress(residentialAddress)
                        .shippingAddress(shippingAddress)
                        .build();

                customers.add(customer);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    private Address parseAddress(String addressData) {

        if (addressData == null || addressData.isEmpty()) return null;

        String[] parts = addressData.split(":");

        if (parts.length < 9) {
            throw new RuntimeException("Invalid address: " + addressData);
        }

        return Address.builder()
                .houseNo(parts[0].trim())
                .building(parts[1].trim())
                .landMark(parts[2].trim())
                .street(parts[3].trim())
                .city(parts[4].trim())
                .district(parts[5].trim())
                .state(parts[6].trim())
                .country(parts[7].trim())
                .zipCode(Integer.parseInt(parts[8].trim()))
                .build();
    }

}
