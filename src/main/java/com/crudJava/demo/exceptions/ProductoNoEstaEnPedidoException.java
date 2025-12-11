package com.crudJava.demo.exceptions;

public class ProductoNoEstaEnPedidoException extends RuntimeException {
    public ProductoNoEstaEnPedidoException(String message) {
        super(message);
    }
}
