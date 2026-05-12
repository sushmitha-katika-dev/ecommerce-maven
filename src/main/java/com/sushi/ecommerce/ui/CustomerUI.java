package com.sushi.ecommerce.ui;

import com.sushi.ecommerce.controller.CustomerController;
import com.sushi.ecommerce.enums.Gender;
import com.sushi.ecommerce.exception.ProductNotFoundException;
import com.sushi.ecommerce.factory.AppFactory;
import com.sushi.ecommerce.model.Address;
import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.model.Product;

import javax.management.openmbean.CompositeData;
import java.util.List;


public class CustomerUI {
    private Customer customer;
    private CustomerController customerController;

    public CustomerUI(Customer customer) {
        this.customer = customer;
        try {
            customerController = AppFactory.getCustomerController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Add New Customer");
            System.out.println("2. Get Customer Details");
            System.out.println("3. Get All Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("0. Exit");

            int choice = InputUtil.getInt("Enter choice: ");

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> getCustomerById();
                case 3 -> getAllCustomers();
                case 4 -> update();
                case 5 -> delete();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addCustomer() {
        try {
            Customer customer = Customer.builder()
                    .id(InputUtil.getInt("Id: "))
                    .name(InputUtil.getString("Name: "))
                    .password(InputUtil.getString("password: "))
                    .email(InputUtil.getString("email: "))
                    .build();
            Customer saved = customerController.save(customer);
            System.out.println("Saved: " + saved);

            System.out.println("Customer added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getCustomerById() {
        try {
            int id = InputUtil.getInt("Enter customer id: ");
            printHeader();
            printCustomerRow(customerController.getById(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllCustomers() {
        List<Customer> customersList = customerController.getAll();
        printHeader();
        for (Customer customer : customersList) {
            printCustomerRow(customer);
        }
    }


    private void update() {
        try {
            int id = InputUtil.getInt("Enter customer ID: ");
            Customer customer = customerController.getById(id);

            Customer updatedData = Customer.builder()
                    .name(InputUtil.getString("New Name: "))
                    .email(InputUtil.getString("New email: "))
                    .password(InputUtil.getString("New password: "))
                    .age(InputUtil.getByte("New age: "))
                    .gender(Gender.valueOf(InputUtil.getString("gender: ").toUpperCase()))
                    .build();


            Customer updated = customerController.update(updatedData);

            System.out.println("Updated!" + updated);

        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    private void delete() {
        try {
            int id = InputUtil.getInt("Enter customer ID: ");

            customerController.delete(id);

            System.out.println("Deleted successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printHeader() {
        System.out.printf(
                "%-5s %-15s %-25s %-15s %-5s %-8s %-10s %-12s %-20s %-20s %-50s %-50s%n",
                "ID", "Name", "Email", "Phone", "Age",
                "Gender", "Status", "Membership",
                "Created On", "Last Login",
                "Residential Address", "Shipping Address"
        );

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static String formatAddress(Address a) {
        if (a == null) return "N/A";

        return String.format("%s, %s, %s, %s, %s, %s, %s - %d",
                a.getHouseNo(),
                a.getBuilding(),
                a.getLandMark(),
                a.getStreet(),
                a.getCity(),
                a.getState(),
                a.getCountry(),
                a.getZipCode()
        );
    }
    public static void printCustomerRow(Customer c) {

        System.out.printf(
                "%-5d %-15s %-25s %-15s %-5d %-8s %-10s %-12s %-20s %-20s %-50s %-50s%n",
                c.getId(),
                c.getName(),
                c.getEmail(),
                c.getPhoneNo(),
                c.getAge(),
                c.getGender(),
                c.getStatus(),
                c.getMembership(),
                c.getCreatedOn(),
                c.getLastLoggedIn(),
                formatAddress(c.getResidentialAddress()),
                formatAddress(c.getShippingAddress())
        );
    }
}
