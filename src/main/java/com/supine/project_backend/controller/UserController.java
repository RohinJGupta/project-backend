package com.supine.project_backend.controller;

import org.springframework.web.bind.annotation.*;

import com.supine.project_backend.model.User;
import com.supine.project_backend.service.UserService;


import jakarta.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users/create-user")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/api/users/update-user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/api/users/get-user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/api/users/delete-user/{id}")
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
