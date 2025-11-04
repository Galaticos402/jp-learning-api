package com.self.jplearning.repository;

import com.self.jplearning.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<SystemUser, UUID> {
    Optional<SystemUser> findByUserId(UUID userId);
}
