package com.apnageneralstore.exception;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String message) {
        super(message);
    }
}
