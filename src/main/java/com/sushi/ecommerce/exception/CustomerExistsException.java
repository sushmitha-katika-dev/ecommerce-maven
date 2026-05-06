package com.sushi.ecommerce.exception;

public class CustomerExistsException extends Exception{
    public CustomerExistsException(String message) {
        super(message);
    }
}
