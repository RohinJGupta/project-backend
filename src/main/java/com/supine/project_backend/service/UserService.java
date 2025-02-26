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
import com.supine.project_backend.dto.NewUserDTO;
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

    public UserDTO createUser(NewUserDTO userDTO) {
        User newUser = new User();

        modelMapper.map(userDTO, newUser); 
        
        if(!newUser.isProvider()) {
            newUser.setServiceProvider(null);
           
        }
        else {
            ServiceProvider serviceProvider = new ServiceProvider();
            Portfolio portfolio = new Portfolio();
            
            // Set up bidirectional relationships
            serviceProvider.setUser(newUser);
            newUser.setServiceProvider(serviceProvider);
            
            portfolio.setServiceProvider(serviceProvider);
            serviceProvider.setPortfolio(portfolio);

            portfolio.setItems(new ArrayList<>());
         
        }


        return modelMapper.map(userRepository.save(newUser), UserDTO.class);
    }

    
    private User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public UserDTO getUser(Long id) {
        User existingUser = getUserById(id);
        if(existingUser != null ) {
            return modelMapper.map(existingUser, UserDTO.class);
        }
        return null;   
    }

   
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            modelMapper.map(userDTO, existingUser);
            return modelMapper.map(userRepository.save(existingUser), UserDTO.class);
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
