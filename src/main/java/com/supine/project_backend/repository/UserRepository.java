package com.supine.project_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supine.project_backend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(?1)")
    Optional<User> findByEmail(String email);
}
