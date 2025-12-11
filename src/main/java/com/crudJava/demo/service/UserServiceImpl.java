package com.crudJava.demo.service;

import com.crudJava.demo.dto.request.UserCreateRequestDTO;
import com.crudJava.demo.dto.response.UserResponseDTO;
import com.crudJava.demo.entity.User;
import com.crudJava.demo.exceptions.EmailYaUsadoException;
import com.crudJava.demo.exceptions.UsuarioInexistenteException;
import com.crudJava.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;


    @Override
    public UserResponseDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsuarioInexistenteException("El usuario no existe")
        );
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsuarioInexistenteException("El usuario no existe")
        );
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public List<UserResponseDTO> findUserByName(String name) {
        List<User> usuarios = userRepository.findAllByName(name);

        if (usuarios.isEmpty()) {
            throw new UsuarioInexistenteException("El usuario no existe");
        } else {

            return usuarios.stream().map(
                    user -> new UserResponseDTO(
                            user.getId(),
                            user.getName(),
                            user.getEmail()
                    )).collect(Collectors.toList()
            );
        }
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO user) {
        if (!userRepository.findByEmail(user.email()).isEmpty()){
            throw new EmailYaUsadoException("El email ya se encuentra registrado.");
        }

        User userFinal = new User();

        userFinal.setEmail(user.email());
        userFinal.setName(user.name());

        userRepository.save(userFinal);

        return new UserResponseDTO(
                userFinal.getId(),
                userFinal.getEmail(),
                userFinal.getName()
        );
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserCreateRequestDTO user) {
        User userEncontrado = userRepository.findById(id).orElseThrow(
                () -> new UsuarioInexistenteException("El usuario no existe")
        );

        userEncontrado.setEmail(user.email());
        userEncontrado.setName(user.name());

        userRepository.save(userEncontrado);

        return new UserResponseDTO(
                userEncontrado.getId(),
                userEncontrado.getEmail(),
                userEncontrado.getName()
        );


    }

    @Override
    public void deleteUser(Long id) {
        User userEncontrado = userRepository.findById(id).orElseThrow(
                () -> new UsuarioInexistenteException("El usuario no existe")
        );
        userRepository.deleteById(id);

    }

    @Override
    public List<UserResponseDTO> getAll() {

        System.out.println(userRepository.findAll());

        return userRepository.findAll().stream().map(
                user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                )).collect(Collectors.toList());
    }

    @Override
    public User findModelById(Long aLong) {
        return userRepository.findById(aLong).orElseThrow(
                () -> new UsuarioInexistenteException("El usuario no existe")
        );
    }
}
