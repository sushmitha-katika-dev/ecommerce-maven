package com.sushi.ecommerce.ui;

import com.sushi.ecommerce.controller.ProductController;
import com.sushi.ecommerce.exception.ProductNotFoundException;
import com.sushi.ecommerce.factory.AppFactory;
import com.sushi.ecommerce.model.Product;

import java.util.List;

public class ProductUI {
    private final ProductController productController;

    public ProductUI() {
        try {
            productController = AppFactory.getProductController();
        } catch (Exception e){
            throw new RuntimeException("Failed to initialize ProductUI", e);
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Add New Product");
            System.out.println("2. Get Product Details");
            System.out.println("3. Get All Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("0. Exit");

            int choice = InputUtil.getInt("Enter choice: ");

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> getProduct();
                case 3 -> getAllProducts();
                case 4 -> updateProduct();
                case 5 -> deleteProduct();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addProduct() {
        try {
            Product product = Product.builder()
                    .id(InputUtil.getInt("ID: "))
                    .name(InputUtil.getString("Name: "))
                    .maxRetailPrice(InputUtil.getDouble("MRP: "))
                    .discountPercentage(InputUtil.getDouble("Discount: "))
                    .company(InputUtil.getString("Company: "))
                    .category(InputUtil.getString("Category: "))
                    .manufacturedYear(InputUtil.getInt("Manufactured Year: "))
                    .build();

            Product saved = productController.save(product);
            System.out.println("Saved: " + saved);

            System.out.println("Product added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getProduct() {
        try {
            int id = InputUtil.getInt("Enter Product ID: ");
            printProduct(productController.getById(id));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void getAllProducts() {
        List<Product> list = productController.getAll();
        printProductList(list);
    }

    private void updateProduct() {
        try {
            int id = InputUtil.getInt("Enter Product ID: ");
            Product product = productController.getById(id);

            Product updatedData = Product.builder()
                    .name(InputUtil.getString("New Name: "))
                    .maxRetailPrice(InputUtil.getDouble("New MRP: "))
                    .discountPercentage(InputUtil.getDouble("New Discount: "))
                    .company(InputUtil.getString("New Company: "))
                    .manufacturedYear(InputUtil.getInt("Year: "))
                    .build();


            Product updated = productController.update(id, updatedData);

            System.out.println("Updated!" + updated);

        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProduct() {
        try {
            int id = InputUtil.getInt("Enter Product ID: ");
            productController.delete(id);
            System.out.println("Deleted!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printProductHeader() {
        System.out.printf(
                "%-5s %-20s %-10s %-10s %-10s %-12s %-15s %-15s %-8s%n",
                "ID", "Name", "MRP", "Discount%", "FinalPrice",
                "Available", "Company", "Category", "Year"
        );

        System.out.println("---------------------------------------------------------------------------------------------------------");
    }

    private static String trim(String value, int max) {
        if (value == null) return "";
        return value.length() > max ? value.substring(0, max - 3) + "..." : value;
    }

    public  void printProductList(List<Product> products) {

        for (Product p : products) {
            printProduct(p);
        }

        System.out.println("----------------------------------------------------------------------------------------------------------\n");
    }

    public  void printProduct(Product p) {

        if (p == null) {
            System.out.println("Product not found!");
            return;
        }

        printProductHeader();

        System.out.printf(
                "%-5d %-20s %-10.2f %-10.2f %-10.2f %-12s %-15s %-15s %-8s%n",
                p.getId(),
                trim(p.getName(), 20),
                p.getMaxRetailPrice(),
                p.getDiscountPercentage(),
                p.getFinalPrice(),
                p.isAvailable() ? "YES" : "NO",
                trim(p.getCompany(), 15),
                trim(p.getCategory(), 15),
                p.getManufacturedYear()
        );
    }

}
