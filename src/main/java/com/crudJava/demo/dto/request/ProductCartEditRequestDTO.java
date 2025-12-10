package com.crudJava.demo.dto.request;

public record ProductCartEditRequestDTO        (
        Long cartID,
        Long productId,
        Integer quantity
) {
}
