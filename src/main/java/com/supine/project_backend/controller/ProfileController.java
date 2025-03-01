package com.supine.project_backend.controller;

import org.springframework.web.bind.annotation.*;

import com.supine.project_backend.dto.NewUserDTO;
import com.supine.project_backend.dto.ProfileDTO;
import com.supine.project_backend.service.UserService;
import com.supine.project_backend.model.Profile;

import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/api/users/me")
    public ResponseEntity<ProfileDTO> getMe() {
        //TODO - Along with other "me" APIs - Might only be Get
        return null;
    }
       

    @GetMapping("/api/users/{id}")
    public ResponseEntity<ProfileDTO> getUser(@PathVariable Long id) {
        ProfileDTO res = userService.getUser(id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/api/users")
    public ResponseEntity<ProfileDTO> createUser(@Valid @RequestBody ProfileDTO userDTO) {
        ProfileDTO res = userService.createUser(userDTO);
        if(res != null) {
            return ResponseEntity.created(URI.create("/api/users/" + res.getId())).body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }




    @PutMapping("/api/users/{id}")
    public ResponseEntity<ProfileDTO> updateUser(@PathVariable Long id, @Valid @RequestBody ProfileDTO userDTO) {
        ProfileDTO res = userService.updateUser(id, userDTO);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        
        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}


    //will need to seperate /api/users/me and /api/users/get-user/{id} using auth first and path var second