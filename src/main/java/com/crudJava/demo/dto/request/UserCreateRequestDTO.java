package com.crudJava.demo.dto.request;

public record UserCreateRequestDTO(
        String email,
        String name
) {
}
