package com.supine.project_backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.dto.ServiceProviderDTO;
import com.supine.project_backend.model.ServiceProvider;
import com.supine.project_backend.model.Profile;
import com.supine.project_backend.repository.ServiceProviderRepository;
import com.supine.project_backend.repository.ProfileRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceProviderService {
    @Autowired
    ServiceProviderRepository serviceProviderRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public ServiceProvider saveServiceProvider(ServiceProviderDTO serviceProviderDTO) {
        ServiceProvider serviceProvider = modelMapper.map(serviceProviderDTO, ServiceProvider.class);
        return serviceProviderRepository.save(serviceProvider);
    }

    private ServiceProvider getServiceProviderFromprofileId(Long profile_id) {
        Profile existingProfile = profileRepository.findById(profile_id).orElse(null);

        if(existingProfile == null) {
            return null;
        }

        // ServiceProvider existingSP = serviceProviderRepository.findById(existingprofile.getServiceProvider().getId()).orElse(null);
        ServiceProvider existingSP = existingProfile.getServiceProvider();
        return existingSP;
    }


    public ServiceProviderDTO getServiceProvider(Long profile_id) {
        return modelMapper.map(getServiceProviderFromprofileId(profile_id), ServiceProviderDTO.class);
    }

  
    public ServiceProviderDTO updateServiceProvider(Long profile_id, ServiceProviderDTO spDTO) {
        ServiceProvider existingSP = getServiceProviderFromprofileId(profile_id);
        if (existingSP != null) {
            modelMapper.map(spDTO, existingSP);
            return modelMapper.map(serviceProviderRepository.save(existingSP), ServiceProviderDTO.class);
        }
        return null;
    }





    //public ServiceProvider findByLocation() {

    //}

      // public ServiceProviderDTO getServiceProvider(Long profile_id) {
    //     profile existingprofile = profileRepository.findById(profile_id).orElse(null);
    //     if (existingprofile == null) {
    //         return null;
    //     }
    //     ServiceProvider existingSP = existingprofile.getServiceProvider();
   
    // }
    
}
