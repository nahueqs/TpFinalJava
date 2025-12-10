package com.crudJava.demo.dto.response;

import java.util.List;

public record CartResponseDTO(
        Long userID,
        List<ProductCartResponseDTO> products
) {
}
