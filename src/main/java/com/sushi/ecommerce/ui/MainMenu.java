package com.sushi.ecommerce.ui;

import com.sushi.ecommerce.factory.AppFactory;

import java.io.IOException;

public class MainMenu {

    public static void start() throws IOException {
        while (true) {
            System.out.println("\n-------------------------------");
            System.out.println("Welcome to e-commerce");
            System.out.println("-------------------------------");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Exit");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1 -> AppFactory.getAuthUI().login();
                case 2 -> AppFactory.getAuthUI().signup();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
