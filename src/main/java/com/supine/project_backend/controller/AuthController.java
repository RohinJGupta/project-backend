package com.supine.project_backend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.supine.project_backend.config.auth.TokenProvider;
import com.supine.project_backend.dto.JwtDTO;
import com.supine.project_backend.dto.SignInDTO;
import com.supine.project_backend.dto.SignUpDTO;
import com.supine.project_backend.model.User;
import com.supine.project_backend.service.AuthService;

import jakarta.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<?> signUp (@RequestBody @Valid SignUpDTO signUpDTO) {
        User res = authService.signUp(signUpDTO);
        if (res != null) {
            return ResponseEntity.created(URI.create("/api/users/" + res.getId())).body(res);
        }

        return ResponseEntity.badRequest().body("User already exists!");
    }

    @PostMapping("/api/v1/auth/signin")
    public ResponseEntity<JwtDTO> signIn(@RequestBody @Valid SignInDTO signInDTO) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(signInDTO.getEmail(), signInDTO.getPassword());
            Authentication authUser = authenticationManager.authenticate(usernamePassword);
            String accessToken = tokenProvider.generateToken((User)authUser.getPrincipal());
            return ResponseEntity.ok(new JwtDTO(accessToken));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    
}
