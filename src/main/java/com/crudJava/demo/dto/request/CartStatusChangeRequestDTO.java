package com.crudJava.demo.dto.request;

import com.crudJava.demo.enums.CartStatus;

public record CartStatusChangeRequestDTO(
        Long cartId,
        String status
) {
}
