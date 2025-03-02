package com.supine.project_backend.controller;

import org.springframework.web.bind.annotation.*;

import com.supine.project_backend.dto.ProfileDTO;
import com.supine.project_backend.service.ProfileService;

import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;



    @GetMapping("/api/profiles/me")
    public ResponseEntity<ProfileDTO> getMe() {
        //TODO - Along with other "me" APIs - Might only be Get
        return null;
    }
       

    @GetMapping("/api/profiles/{id}")
    public ResponseEntity<ProfileDTO> getprofile(@PathVariable Long id) {
        ProfileDTO res = profileService.getProfile(id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/api/profiles")
    public ResponseEntity<ProfileDTO> createprofile(@Valid @RequestBody ProfileDTO profileDTO) {
        ProfileDTO res = profileService.createProfile(profileDTO);
        if(res != null) {
            return ResponseEntity.created(URI.create("/api/profiles/" + res.getId())).body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }




    @PutMapping("/api/profiles/{id}")
    public ResponseEntity<ProfileDTO> updateprofile(@PathVariable Long id, @Valid @RequestBody ProfileDTO profileDTO) {
        ProfileDTO res = profileService.updateProfile(id, profileDTO);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/profiles/{id}")
    public ResponseEntity<Void> deleteprofile(@PathVariable Long id) {
        boolean isDeleted = profileService.deleteProfile(id);
        
        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}


    //will need to seperate /api/profiles/me and /api/profiles/get-profile/{id} using auth first and path var second