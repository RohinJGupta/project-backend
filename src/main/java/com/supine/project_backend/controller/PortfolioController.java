package com.supine.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.supine.project_backend.service.PortfolioService;

import jakarta.validation.Valid;

import com.supine.project_backend.config.auth.TokenProvider;
import com.supine.project_backend.dto.PortfolioDTO;
import com.supine.project_backend.dto.PortfolioItemDTO;

import java.net.URI;
import java.util.List;



@RestController
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private TokenProvider tokenProvider;

    
    @GetMapping("/api/v1/portfolios/me")
    public ResponseEntity<PortfolioDTO> getPortfolio(@RequestHeader("Authorization") String authToken) {
        //TODO - Along with other "me" APIs - Might only be Get
        authToken = authToken.replace("Bearer ", "");
        Long user_id = tokenProvider.getIdFromJwt(authToken);
        PortfolioDTO res = portfolioService.getPortfolio(user_id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/v1/portfolios/all")
    public ResponseEntity<List<PortfolioItemDTO>> getAllItems(@RequestHeader("Authorization") String authToken) {
        //TODO - Along with other "me" APIs - Might only be Get
        authToken = authToken.replace("Bearer ", "");
        Long user_id = tokenProvider.getIdFromJwt(authToken);
        List<PortfolioItemDTO> res = portfolioService.getAllItems(user_id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/v1/portfolios/me")
    public ResponseEntity<PortfolioItemDTO> addPortfolioItem(@RequestHeader("Authorization") String authToken, @Valid @RequestBody PortfolioItemDTO portfolioItemDTO) {
        authToken = authToken.replace("Bearer ", "");
        Long user_id = tokenProvider.getIdFromJwt(authToken);
        PortfolioItemDTO pItemDTO = portfolioService.addPortfolioItem(user_id, portfolioItemDTO);
        if(pItemDTO != null) {
            return ResponseEntity.created(URI.create("/api/portfolios/" + user_id + "/items/" + pItemDTO.getId())).body(pItemDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/v1/portfolios/me/{item-id}")
    public ResponseEntity<PortfolioDTO> deletePortfolioItem(@RequestHeader("Authorization") String authToken, @PathVariable Long item_id) {
        authToken = authToken.replace("Bearer ", "");
        Long user_id = tokenProvider.getIdFromJwt(authToken);
        boolean isDeleted = portfolioService.deletePortfolioItem(user_id, item_id);
        
        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


        


    @PutMapping("/api/v1/portfolios/me")
    public ResponseEntity<PortfolioDTO> updatePortfolio(@RequestHeader("Authorization") String authToken, @Valid @RequestBody PortfolioDTO portfolioDTO) {
        authToken = authToken.replace("Bearer ", "");
        Long user_id = tokenProvider.getIdFromJwt(authToken);
        PortfolioDTO res = portfolioService.updatePortfolio(user_id, portfolioDTO);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/api/v1/portfolios/{user_id}")
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
    // @GetMapping("/api/v1/portfolios/all/{user_id}")
    // public ResponseEntity<List<PortfolioItemDTO>> getAllItems(@PathVariable Long user_id) {
    //     List<PortfolioItemDTO> list = portfolioService.getAllItems(user_id);

    //     if(list == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     else if (list.isEmpty()) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     else {
    //         return ResponseEntity.ok().body(list);
    //     }
    // }

}


    //201 for post
    // Implement logic to update an existing portfolio item
    //will need to seperate /api/sp/me and /api/users/get-sp/{id} using auth first and path var second