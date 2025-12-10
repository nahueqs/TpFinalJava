package com.crudJava.demo.controller;

import com.crudJava.demo.dto.response.UserResponseDTO;
import com.crudJava.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    UserResponseDTO createUser(){
        return null;
    }

    @GetMapping
    List<UserResponseDTO> getAllUsers(){
        System.out.println(userService.getAll());
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
