package com.crudJava.demo.dto.request;

import java.util.List;

public record CartCreateRequestDTO(
        Long idUser,
        List<ProductCartRequestDTO> productCartRequestDTOList
) {
}
