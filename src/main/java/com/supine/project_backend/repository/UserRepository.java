package com.supine.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supine.project_backend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
