package com.crudJava.demo.dto.request;

public record ProductUpdateRequestDTO(
        String name,
        String desc,
        float price,
        Long categoryId
) {
}
