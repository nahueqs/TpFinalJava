package com.crudJava.demo.service;

import com.crudJava.demo.dto.response.UserResponseDTO;
import com.crudJava.demo.entity.User;
import com.crudJava.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;


    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserByName(String name) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserResponseDTO getAll() {
        return userRepository.findAll().stream().map(
                user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                )).toList().get(0);
    }
}
