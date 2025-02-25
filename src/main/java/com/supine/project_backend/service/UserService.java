package com.supine.project_backend.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supine.project_backend.model.User;
import com.supine.project_backend.repository.UserRepository;
import com.supine.project_backend.model.ServiceProvider;
import com.supine.project_backend.model.Portfolio;
import java.time.*;

@Service
public class UserService {

    //TODO - need to figure out transaction management in case of failed update, create, etc.

    @Autowired
    private UserRepository userRepository;

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

    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

   
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getUsername()); 
            existingUser.setIsProvider(user.isProvider());
            if(user.isProvider() && existingUser.getServiceProvider() == null) {
                ServiceProvider serviceProvider = new ServiceProvider();
                Portfolio portfolio = new Portfolio();
    
                existingUser.setServiceProvider(serviceProvider);
                serviceProvider.setUser(existingUser);
    
                serviceProvider.setPortfolio(portfolio);
                portfolio.setServiceProvider(serviceProvider);
    
                portfolio.setItems(new ArrayList<>());
            }
            else if (!user.isProvider()){
                existingUser.setServiceProvider(null);
            }
            existingUser.setPhone(user.getPhone());
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
