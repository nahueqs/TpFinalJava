package com.crudJava.demo.dto.response;

public record ProductDetailResponseDTO(
        Long productId,
        String name,
        String desc,
        float price,
        Integer category,
        Integer stock
) {
}
