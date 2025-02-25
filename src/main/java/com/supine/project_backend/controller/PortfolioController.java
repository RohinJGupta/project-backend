package com.supine.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supine.project_backend.service.PortfolioService;

import jakarta.validation.Valid;

import com.supine.project_backend.model.Portfolio;
import com.supine.project_backend.model.PortfolioItem;
import java.util.List;

//will need to seperate /api/sp/me and /api/users/get-sp/{id} using auth first and path var second

@RestController
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/api/portfolios/get-portfolio/{user_id}")
    public Portfolio getPortfolio(@PathVariable Long user_id) {
        return portfolioService.getPortfolio(user_id);
    }

    @GetMapping("/api/portfolios/get-all-items/{user_id}")
    public List<PortfolioItem> getAllItems(@PathVariable Long user_id) {
        return portfolioService.getAllItems(user_id);
    }


    @PostMapping("/api/portfolios/add-item/{user_id}")
    public Portfolio addPortfolioItem(@PathVariable Long user_id, @Valid @RequestBody PortfolioItem portfolioItem) {
        // Implement logic to add a new portfolio item
        return portfolioService.addPortfolioItem(user_id, portfolioItem);
    }

    @PutMapping("/api/portfolios/update-portfolio/{user_id}")
    public Portfolio updatePortfolio(@PathVariable Long user_id, @Valid @RequestBody Portfolio portfolio) {
        // Implement logic to update an existing portfolio item
        return portfolioService.updatePortfolio(user_id, portfolio);
    }
}
