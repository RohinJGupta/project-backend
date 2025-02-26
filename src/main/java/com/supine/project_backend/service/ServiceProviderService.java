package com.supine.project_backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.dto.ServiceProviderDTO;
import com.supine.project_backend.model.ServiceProvider;
import com.supine.project_backend.model.User;
import com.supine.project_backend.repository.ServiceProviderRepository;
import com.supine.project_backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceProviderService {
    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public ServiceProvider saveServiceProvider(ServiceProviderDTO serviceProviderDTO) {
        ServiceProvider serviceProvider = modelMapper.map(serviceProviderDTO, ServiceProvider.class);
        return serviceProviderRepository.save(serviceProvider);
    }

    private ServiceProvider getServiceProviderFromUserId(Long user_id) {
        User existingUser = userRepository.findById(user_id).orElse(null);

        if(existingUser == null) {
            return null;
        }

        // ServiceProvider existingSP = serviceProviderRepository.findById(existingUser.getServiceProvider().getId()).orElse(null);
        ServiceProvider existingSP = existingUser.getServiceProvider();
        return existingSP;
    }

    public ServiceProviderDTO getServiceProvider(Long user_id) {
        User existingUser = userRepository.findById(user_id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        ServiceProvider existingSP = existingUser.getServiceProvider();
        return modelMapper.map(existingSP, ServiceProviderDTO.class);
    }

    public ServiceProvider updateServiceProvider(Long user_id, ServiceProviderDTO spDTO) {
        ServiceProvider existingSP = getServiceProviderFromUserId(user_id);
        if (existingSP != null) {
            modelMapper.map(spDTO, existingSP);
            return serviceProviderRepository.save(existingSP);
        }
        return null;
    }

    //deal with null in controller
    public ServiceProvider getServiceProvider(Long user_id) {
        return getServiceProviderFromUserId(user_id);
    }

    //public ServiceProvider findByLocation() {

    //}
    
}
