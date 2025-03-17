package com.supine.project_backend.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.supine.project_backend.service.VendorService;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.validation.Valid;

import com.supine.project_backend.config.auth.TokenProvider;
import com.supine.project_backend.dto.UserDTO;
import com.supine.project_backend.dto.VendorDTO;


@RestController
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @Autowired
    private TokenProvider tokenProvider;


    @GetMapping("/api/v1/providers/me")
    public ResponseEntity<VendorDTO> getVendor(@RequestHeader("Authorization") String authToken) {
        //TODO - Along with other "me" APIs - Might only be Get
        authToken = authToken.replace("Bearer ", "");

        Long user_id = tokenProvider.getIdFromJwt(authToken);
        VendorDTO res = vendorService.getVendor(user_id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/v1/providers/me")
    public ResponseEntity<VendorDTO> updateVendor(@RequestHeader("Authorization") String authToken, @Valid @RequestBody VendorDTO vendorDTO) {
        authToken = authToken.replace("Bearer ", "");

        Long user_id = tokenProvider.getIdFromJwt(authToken);
        VendorDTO res = vendorService.updateVendor(user_id, vendorDTO);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/api/v1/providers/{user_id}")
    public ResponseEntity<VendorDTO> getVendor(@PathVariable Long user_id) {
        //if null return null
        VendorDTO res = vendorService.getVendor(user_id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



}


    // @PostMapping("/api/service-providers/create-sp/{user_id}")
    // public vendor createvendor(@PathVariable Long user_id, @Valid @RequestBody vendor sp) {
    //     return vendorService.createvendor(user_id, sp);
    // }

    //will need to seperate /api/sp/me and /api/users/get-sp/{id} using auth first and path var second