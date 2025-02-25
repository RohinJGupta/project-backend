package com.supine.project_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.supine.project_backend.model.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    
}
