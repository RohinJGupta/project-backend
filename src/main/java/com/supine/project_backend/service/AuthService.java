package com.supine.project_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.supine.project_backend.dto.SignUpDTO;
import com.supine.project_backend.model.User;
import com.supine.project_backend.repository.UserRepository;

import jakarta.transaction.Transactional;

public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    private User saveUser(User user) {
      return userRepository.save(user);
    }

    public UserDetails loadUserByEmail(String email) {
        UserDetails user = userRepository.findByEmail(email).orElse(null);
        return user;
    }

    public UserDetails signUp(SignUpDTO signUpDTO) throws Exception {
        if (loadUserByEmail(signUpDTO.getEmail()) != null) {
            throw new Exception("User already exists!");
        }
        String passwordHash = new BCryptPasswordEncoder().encode(signUpDTO.getPassword());
        User newUser = new User(signUpDTO.getEmail(), passwordHash, signUpDTO.getUserRole());
        return saveUser(newUser);
           
    }
}
