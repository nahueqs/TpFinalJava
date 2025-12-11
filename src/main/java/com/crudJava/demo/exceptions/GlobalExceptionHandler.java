package com.crudJava.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursosInexistenteException.class)
    public ResponseEntity<String> handleRecursosInexistenteException(RecursosInexistenteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CantidadPedidoInvalidaException.class)
    public ResponseEntity<String> handleCantidadPedidoInvalidaException(CantidadPedidoInvalidaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductoNoEstaEnPedidoException.class)
    public ResponseEntity<String> handleProductoNoEstaEnPedidoException(ProductoNoEstaEnPedidoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductoYaEnCarrito.class)
    public ResponseEntity<String> handleProductoYaEnCarrito(ProductoYaEnCarrito ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailYaUsadoException.class)
    public ResponseEntity<String> handleEmailYaUsadoException(EmailYaUsadoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
