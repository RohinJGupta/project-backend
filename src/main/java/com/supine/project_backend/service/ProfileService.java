package com.supine.project_backend.service;

import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supine.project_backend.model.Profile;
import com.supine.project_backend.repository.UserRepository;
import com.supine.project_backend.model.ServiceProvider;
import com.supine.project_backend.model.Portfolio;
import com.supine.project_backend.dto.NewUserDTO;
import com.supine.project_backend.dto.ProfileDTO;


@Service
public class UserService {

    //TODO - need to figure out transaction management in case of failed update, create, etc.

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public Profile saveUser(Profile user) {
        return userRepository.save(user);
    }


    //modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    //Look into using the above instead of NewUserDTO with Integer wrapper class
    public ProfileDTO createUser(ProfileDTO userDTO) {
        Profile newUser = new Profile();

        modelMapper.map(userDTO, newUser); 
        
        if(!newUser.isProvider()) {
            newUser.setServiceProvider(null);
        } else {
            ServiceProvider serviceProvider = new ServiceProvider();
            Portfolio portfolio = new Portfolio();
            
            // Set up bidirectional relationships
            serviceProvider.setProfile(newUser);
            newUser.setServiceProvider(serviceProvider);
            
            portfolio.setServiceProvider(serviceProvider);
            serviceProvider.setPortfolio(portfolio);

            portfolio.setItems(new ArrayList<>());
        }

        return modelMapper.map(userRepository.save(newUser), ProfileDTO.class);
    }

    
    private Profile getUserById(Long id) {
        Profile user = userRepository.findById(id).orElse(null);
        return user;
    }

    public ProfileDTO getUser(Long id) {
        Profile existingUser = getUserById(id);
        if(existingUser != null ) {
            return modelMapper.map(existingUser, ProfileDTO.class);
        }
        return null;   
    }

   
    public ProfileDTO updateUser(Long id, ProfileDTO userDTO) {
        Profile existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            modelMapper.map(userDTO, existingUser);
            return modelMapper.map(userRepository.save(existingUser), ProfileDTO.class);
        }
        return null;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        Profile user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }
    

}
