package com.sushi.ecommerce.ui;

import com.sushi.ecommerce.model.Customer;

public class DashboardUI {

    public void show() {
        while (true) {
            System.out.println("1. Customer");
            System.out.println("2. Product");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1 -> new CustomerUI().menu();
                case 2 -> new ProductUI().menu();
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}