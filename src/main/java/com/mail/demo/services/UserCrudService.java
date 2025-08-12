package com.mail.demo.services;

import com.mail.demo.dtos.User.CreateUserRequest;
import com.mail.demo.dtos.User.CreateUserResponse;
import com.mail.demo.entities.UserEntity;
import com.mail.demo.providers.email.JavaEmailProvider;
import com.mail.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserCrudService {

    private final UserRepository userRepository;
    private final JavaEmailProvider javaEmailProvider;

    public UserCrudService(UserRepository userRepository,  JavaEmailProvider javaEmailProvider) {
        this.userRepository = userRepository;
        this.javaEmailProvider = javaEmailProvider;
    }

    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        this.userRepository.findByEmail(createUserRequest.getEmail())
                .ifPresent((user) -> {
                    throw new RuntimeException();
                });

        UserEntity user = UserEntity.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .build();

        UserEntity savedUser = userRepository.save(user);

        Map<String,Object> mailVariables = Map.of(
                "name", savedUser.getName()
        );

        this.javaEmailProvider.sendEmail(savedUser.getEmail(), "Bem Vindo ao Nosso Sistema!", "user-create.html", mailVariables);

        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }
}
