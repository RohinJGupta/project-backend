package com.supine.project_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supine.project_backend.model.PortfolioItem;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long>{
    
}
