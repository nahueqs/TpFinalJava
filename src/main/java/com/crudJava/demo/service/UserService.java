package com.crudJava.demo.service;

import com.crudJava.demo.dto.response.UserResponseDTO;
import com.crudJava.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserById(Long id);

    User findUserByEmail(String email);

    User findUserByName(String name);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    UserResponseDTO getAll();
}
