package com.supine.project_backend.service;

import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supine.project_backend.model.User;
import com.supine.project_backend.repository.UserRepository;
import com.supine.project_backend.model.ServiceProvider;
import com.supine.project_backend.model.Portfolio;
import com.supine.project_backend.dto.UserDTO;


@Service
public class UserService {

    //TODO - need to figure out transaction management in case of failed update, create, etc.

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(User user) {
        if(!user.isProvider()) {
            user.setServiceProvider(null);
           
        }
        else {
            ServiceProvider serviceProvider = new ServiceProvider();
            Portfolio portfolio = new Portfolio();
            
            // Set up bidirectional relationships
            serviceProvider.setUser(user);
            user.setServiceProvider(serviceProvider);
            
            portfolio.setServiceProvider(serviceProvider);
            serviceProvider.setPortfolio(portfolio);

            portfolio.setItems(new ArrayList<>());
         
        }

        // user.setCreatedAt(Instant.now());
        // user.setUpdatedAt(Instant.now());
        return userRepository.save(user);
    }

    
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return modelMapper.map(user, UserDTO.class);
    }

   
    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            modelMapper.map(userDTO, existingUser);
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }
    

}
