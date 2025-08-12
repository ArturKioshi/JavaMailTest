package com.mail.demo.controllers;

import com.mail.demo.dtos.User.CreateUserRequest;
import com.mail.demo.dtos.User.CreateUserResponse;
import com.mail.demo.services.UserCrudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserCrudService userCrudService;

    public UserController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse response = this.userCrudService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
