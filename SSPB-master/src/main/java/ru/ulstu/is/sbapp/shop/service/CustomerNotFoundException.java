package ru.ulstu.is.sbapp.shop.service;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id) {
        super(String.format("Customer with id [%s] is not found", id));
    }
}
