package com.crudJava.demo.dto.response;

public record ProductDetailResponseDTO(
        Long productId,
        String name,
        String desc,
        double price,
        Integer category,
        Integer stock
) {
}
