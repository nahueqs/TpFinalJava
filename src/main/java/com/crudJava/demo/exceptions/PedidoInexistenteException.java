package com.crudJava.demo.exceptions;

public class PedidoInexistenteException extends RuntimeException {

  public PedidoInexistenteException(String message) {
    super(message);
  }
}
