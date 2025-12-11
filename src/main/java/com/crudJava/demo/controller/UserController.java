package com.crudJava.demo.controller;

import com.crudJava.demo.dto.request.UserCreateRequestDTO;
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
    UserResponseDTO createUser(UserCreateRequestDTO request){
        return userService.createUser(request);
    }

    @GetMapping
    List<UserResponseDTO> getAllUsers(){
        System.out.println(userService.getAll());
        return userService.getAll();
    }

    @GetMapping("/{id}")
    UserResponseDTO getUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @GetMapping("/email/{email}")
    UserResponseDTO getUserByEmail(@PathVariable String email){
        return userService.findUserByEmail(email);
    }


    @GetMapping("/name/{name}")
    List<UserResponseDTO> getUserByName(@PathVariable String name){
        return userService.findUserByName(name);
    }

    @PutMapping("/{id}")
    UserResponseDTO updateUser(@PathVariable Long id, UserCreateRequestDTO request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }




}
