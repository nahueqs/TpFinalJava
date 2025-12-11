package com.crudJava.demo.dto.request;

import jakarta.persistence.criteria.CriteriaBuilder;

public record ProductCreateRequestDTO(
        String name,
        String desc,
        float price,
        Integer categoryId,
        Integer stock
) {
}
