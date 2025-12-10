package com.crudJava.demo.controller;

import com.crudJava.demo.dto.response.UserResponseDTO;
import com.crudJava.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    UserResponseDTO createUser(){
        return null;
    }

    @GetMapping
    UserResponseDTO getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    UserResponseDTO getUserById(){
        return null;
    }

    @PutMapping("/{id}")
    UserResponseDTO updateUser(){
        return null;
    }

    @DeleteMapping("/{id}")
    UserResponseDTO deleteUser(){
        return null;
    }




}
