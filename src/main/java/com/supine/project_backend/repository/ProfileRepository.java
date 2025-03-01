package com.supine.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supine.project_backend.model.Profile;

public interface UserRepository extends JpaRepository<Profile, Long>{

}
