package com.sushi.ecommerce.exception;

public class CustomerExistsException extends RuntimeException{
    public CustomerExistsException(String message) {
        super(message);
    }
}
