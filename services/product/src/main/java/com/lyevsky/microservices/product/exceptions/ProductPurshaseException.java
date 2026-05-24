package com.lyevsky.microservices.product.exceptions;

public class ProductPurshaseException extends RuntimeException {
    public ProductPurshaseException(String message) {
        super(message);
    }
}