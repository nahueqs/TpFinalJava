package com.crudJava.demo.dto.response;

import com.crudJava.demo.entity.ProductCart;

import java.util.List;

public record CartResponseDTO(
        Long userID,
        List<ProductCartResponseDTO> products
) {
}
