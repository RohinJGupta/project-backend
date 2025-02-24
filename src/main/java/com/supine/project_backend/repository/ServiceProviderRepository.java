package com.supine.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supine.project_backend.model.ServiceProvider;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long>{
    
}
