package com.supine.project_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.supine.project_backend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT USER FROM PUBLIC.USERS WHERE LOWER(USER.EMAIL) = LOWER(?1)")
    Optional<UserDetails> findByEmail(String email);
}
