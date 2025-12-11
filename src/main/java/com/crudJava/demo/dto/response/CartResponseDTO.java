package com.crudJava.demo.dto.response;

import com.crudJava.demo.entity.ProductCart;

import java.util.List;

public record CartResponseDTO(
        Long cartId,
        Long userID,
        String status,
        List<ProductCartResponseDTO> products
) {
}
