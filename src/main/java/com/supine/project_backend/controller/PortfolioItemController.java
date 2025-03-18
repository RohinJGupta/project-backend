package com.supine.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supine.project_backend.dto.PortfolioItemDTO;
import com.supine.project_backend.service.PortfolioItemService;

import jakarta.validation.Valid;

@RestController
public class PortfolioItemController {
    @Autowired
    private PortfolioItemService portfolioItemService;

    @GetMapping("/api/v1/items/{item-id}")
    public ResponseEntity<PortfolioItemDTO> getPortfolioItem(@PathVariable Long item_id) {
        PortfolioItemDTO res = portfolioItemService.getPortfolioItem(item_id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    // @DeleteMapping("/api/v1/items/{item-id}")
    // public ResponseEntity<Void> deletePortfolioItem(@PathVariable Long item_id) {
    //     boolean isDeleted = portfolioItemService.deletePortfolioItem(item_id);
        
    //     if(isDeleted) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     else {
    //         return ResponseEntity.notFound().build();
    //     }
    // } 

    
    @PutMapping("/api/v1/items/{item-id}")
    public ResponseEntity<PortfolioItemDTO> updatePortfolioItem(@PathVariable Long item_id, @Valid @RequestBody PortfolioItemDTO portfolioItemDTO) {
        PortfolioItemDTO res = portfolioItemService.updatePortfolioItem(item_id, portfolioItemDTO);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
