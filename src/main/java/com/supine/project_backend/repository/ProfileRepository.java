package com.supine.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supine.project_backend.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
