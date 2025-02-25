package com.supine.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supine.project_backend.model.PortfolioItem;
import com.supine.project_backend.service.PortfolioItemService;

import jakarta.validation.Valid;

@RestController
public class PortfolioItemController {
    @Autowired
    private PortfolioItemService portfolioItemService;

    @GetMapping("/api/portfolioitems/get-item/{item-id}")
    public PortfolioItem getPortfolioItem(@PathVariable Long item_id) {
        return portfolioItemService.getPortfolioItem(item_id);
    }


    @DeleteMapping("/api/portfolioitems/remove-item/{item-id}")
    public ResponseEntity<Void> deletePortfolioItem(@PathVariable Long item_id) {
        boolean isDeleted = portfolioItemService.deletePortfolioItem(item_id);
        
        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    } 

    
    @PutMapping("/api/portfolioitems/update-item/{item-id}")
    public PortfolioItem updatePortfolioItem(@PathVariable Long item_id, @Valid @RequestBody PortfolioItem portfolioItem) {
        return portfolioItemService.updatePortfolioItem(item_id, portfolioItem);
    }
}
