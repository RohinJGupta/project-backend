package com.supine.project_backend.controller;

import org.springframework.web.bind.annotation.*;

import com.supine.project_backend.config.auth.TokenProvider;
import com.supine.project_backend.dto.UserDTO;
import com.supine.project_backend.service.UserService;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;



    @GetMapping("/api/v1/users/me")
    public ResponseEntity<UserDTO> getUser(@RequestHeader("Authorization") String authToken) {
        //TODO - Along with other "me" APIs - Might only be Get
        authToken = authToken.replace("Bearer ", "");

        Long id = tokenProvider.getIdFromJwt(authToken);
        UserDTO res = userService.getUser(id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/v1/users/me")
    public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String authToken, @Valid @RequestBody UserDTO userDTO) {
        authToken = authToken.replace("Bearer ", "");
        Long id = tokenProvider.getIdFromJwt(authToken);
        UserDTO res = userService.updateUser(id, userDTO);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/v1/users/me")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String authToken) {
        authToken = authToken.replace("Bearer ", "");
        Long id = tokenProvider.getIdFromJwt(authToken);
        boolean isDeleted = userService.deleteUser(id);
        
        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
       

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO res = userService.getUser(id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // @GetMapping("/api/v1/users/all")
    // public ResponseEntity<UserDTO> getAllUsers() {
    //     UserDTO res = userService.getUser(id);
    //     if(res != null) {
    //         return ResponseEntity.ok().body(res);
    //     }
    //     else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }


    // @PostMapping("/api/v1/users")
    // public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
    //     UserDTO res = userService.createUser(userDTO);
    //     if(res != null) {
    //         return ResponseEntity.created(URI.create("/api/users/" + res.getId())).body(res);
    //     }
    //     else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }




    // @PutMapping("/api/v1/users/{id}")
    // public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
    //     UserDTO res = userService.updateUser(id, userDTO);
    //     if(res != null) {
    //         return ResponseEntity.ok().body(res);
    //     }
    //     else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @DeleteMapping("/api/v1/users/{id}")
    // public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    //     boolean isDeleted = userService.deleteUser(id);
        
    //     if(isDeleted) {
    //         return ResponseEntity.noContent().build();
    //     }
    //     else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

}


    //will need to seperate /api/users/me and /api/users/get-user/{id} using auth first and path var second