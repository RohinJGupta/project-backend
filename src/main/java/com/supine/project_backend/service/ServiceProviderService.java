package com.supine.project_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Transactional
    public ServiceProvider saveServiceProvider(ServiceProvider serviceProvider) {
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

    public ServiceProvider updateServiceProvider(Long user_id, ServiceProvider sp) {


        ServiceProvider existingSP = getServiceProviderFromUserId(user_id);
        if(existingSP != null) {
            existingSP.setBusinessName(sp.getBusinessName());
            existingSP.setBiography(sp.getBiography());
            existingSP.setCertifications(sp.getCertifications());
            existingSP.setBackgroundCheck(sp.isBackgroundCheck());
            existingSP.setLocation(sp.getLocation());
            existingSP.setAvailability(sp.getAvailability());
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
