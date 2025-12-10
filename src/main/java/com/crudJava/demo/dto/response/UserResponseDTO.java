package com.crudJava.demo.dto.response;

public record UserResponseDTO(
        Long userID,
        String name,
        String email
) {
}
