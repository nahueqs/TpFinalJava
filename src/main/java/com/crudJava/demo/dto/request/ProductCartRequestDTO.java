package com.crudJava.demo.dto.request;

public record ProductCartRequestDTO(
        Long productID,
        Integer quantity
) {
}
