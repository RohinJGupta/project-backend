package com.supine.project_backend.service;

import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supine.project_backend.model.Profile;
import com.supine.project_backend.repository.ProfileRepository;
import com.supine.project_backend.model.ServiceProvider;
import com.supine.project_backend.model.Portfolio;
import com.supine.project_backend.dto.ProfileDTO;


@Service
public class ProfileService {

    //TODO - need to figure out transaction management in case of failed update, create, etc.

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }


    //modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    //Look into using the above instead of NewprofileDTO with Integer wrapper class
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Profile newProfile = new Profile();

        modelMapper.map(profileDTO, newProfile); 
        
        if(!newProfile.isProvider()) {
            newProfile.setServiceProvider(null);
        } else {
            ServiceProvider serviceProvider = new ServiceProvider();
            Portfolio portfolio = new Portfolio();
            
            // Set up bidirectional relationships
            serviceProvider.setProfile(newProfile);
            newProfile.setServiceProvider(serviceProvider);
            
            portfolio.setServiceProvider(serviceProvider);
            serviceProvider.setPortfolio(portfolio);

            portfolio.setItems(new ArrayList<>());
        }

        return modelMapper.map(profileRepository.save(newProfile), ProfileDTO.class);
    }

    
    private Profile getProfileById(Long id) {
        Profile profile = profileRepository.findById(id).orElse(null);
        return profile;
    }

    public ProfileDTO getProfile(Long id) {
        Profile existingProfile = getProfileById(id);
        if(existingProfile != null ) {
            return modelMapper.map(existingProfile, ProfileDTO.class);
        }
        return null;   
    }

   
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        Profile existingProfile = profileRepository.findById(id).orElse(null);
        if (existingProfile != null) {
            modelMapper.map(profileDTO, existingProfile);
            return modelMapper.map(profileRepository.save(existingProfile), ProfileDTO.class);
        }
        return null;
    }

    @Transactional
    public boolean deleteProfile(Long id) {
        Profile profile = profileRepository.findById(id).orElse(null);
        if (profile != null) {
            profileRepository.delete(profile);
            return true;
        }
        return false;
    }
    

}
