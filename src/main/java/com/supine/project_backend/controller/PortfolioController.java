package com.supine.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supine.project_backend.service.PortfolioService;

import jakarta.validation.Valid;

import com.supine.project_backend.dto.NewPortfolioItemDTO;
import com.supine.project_backend.dto.PortfolioDTO;
import com.supine.project_backend.dto.PortfolioItemDTO;
import com.supine.project_backend.dto.ServiceProviderDTO;

import java.net.URI;
import java.util.List;



@RestController
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    
    @GetMapping("/api/portfolios/me")
    public ResponseEntity<ServiceProviderDTO> getMe() {
        //TODO - Along with other "me" APIs - Might only be Get
        return null;
    }

    @GetMapping("/api/portfolios/{user_id}")
    public ResponseEntity<PortfolioDTO> getPortfolio(@PathVariable Long user_id) {
        PortfolioDTO res = portfolioService.getPortfolio(user_id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //Implement "me" equivalent
    @GetMapping("/api/portfolios/all/{user_id}")
    public ResponseEntity<List<PortfolioItemDTO>> getAllItems(@PathVariable Long user_id) {
        List<PortfolioItemDTO> list = portfolioService.getAllItems(user_id);

        if(list == null) {
            return ResponseEntity.notFound().build();
        }
        else if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok().body(list);
        }
    }


    @PostMapping("/api/portfolios/{user_id}")
    public ResponseEntity<PortfolioDTO> addPortfolioItem(@PathVariable Long user_id, @Valid @RequestBody NewPortfolioItemDTO portfolioItemDTO) {
        PortfolioDTO portfolioDTO = portfolioService.addPortfolioItem(user_id, portfolioItemDTO);
        if(portfolioDTO != null) {
            return ResponseEntity.created(URI.create("/api/portfolios/" + user_id + "/items/" + portfolioDTO.getId())).body(portfolioDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/portfolios/{user_id}")
    public ResponseEntity<PortfolioDTO> updatePortfolio(@PathVariable Long user_id, @Valid @RequestBody PortfolioDTO portfolioDTO) {

        PortfolioDTO res = portfolioService.updatePortfolio(user_id, portfolioDTO);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
}


    //201 for post
    // Implement logic to update an existing portfolio item
    //will need to seperate /api/sp/me and /api/users/get-sp/{id} using auth first and path var second