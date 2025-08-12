package com.mail.demo.repositories;

import com.mail.demo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findById(UUID id);
    Optional<UserEntity> findByEmail(String email);
}
