package com.crudJava.demo.exceptions;

public class ProductoYaEnCarrito extends RuntimeException {
    public ProductoYaEnCarrito(String message) {
        super(message);
    }
}
