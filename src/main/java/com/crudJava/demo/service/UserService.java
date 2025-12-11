package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.UserCreateRequestDTO;
import com.crudJava.demo.dto.response.UserResponseDTO;
import com.crudJava.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponseDTO findUserById(Long id);

    UserResponseDTO findUserByEmail(String email);

    List<UserResponseDTO> findUserByName(String name);

    UserResponseDTO createUser(UserCreateRequestDTO user);

    UserResponseDTO updateUser(Long id, UserCreateRequestDTO user);

    void deleteUser(Long id);

    List<UserResponseDTO> getAll();

    User findModelById(Long aLong);
}
