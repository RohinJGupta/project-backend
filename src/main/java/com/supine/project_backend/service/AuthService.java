package com.supine.project_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.supine.project_backend.dto.SignUpDTO;
import com.supine.project_backend.model.User;
import com.supine.project_backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Transactional
    private User saveUser(User user) {
      return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDetails user = userRepository.findByEmail(email).orElse(null);
        return user;
    }

    public User signUp(SignUpDTO signUpDTO) {
        if (loadUserByUsername(signUpDTO.getEmail()) != null) {
            return null;
        }
        String passwordHash = new BCryptPasswordEncoder().encode(signUpDTO.getPassword());
        User newUser = new User(signUpDTO.getEmail(), passwordHash, signUpDTO.getUserRole());
        return saveUser(newUser);
           
    }
}
