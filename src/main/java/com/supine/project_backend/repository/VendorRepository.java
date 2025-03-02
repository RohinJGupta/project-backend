package com.supine.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supine.project_backend.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{
    
}
