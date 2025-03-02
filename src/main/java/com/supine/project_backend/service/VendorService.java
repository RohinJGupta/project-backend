package com.supine.project_backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.dto.VendorDTO;
import com.supine.project_backend.model.Vendor;
import com.supine.project_backend.model.Profile;
import com.supine.project_backend.repository.VendorRepository;
import com.supine.project_backend.repository.ProfileRepository;

import jakarta.transaction.Transactional;

@Service
public class VendorService {
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    private Vendor getVendorFromprofileId(Long profile_id) {
        Profile existingProfile = profileRepository.findById(profile_id).orElse(null);

        if(existingProfile == null) {
            return null;
        }

        // vendor existingSP = vendorRepository.findById(existingprofile.getvendor().getId()).orElse(null);
        Vendor existingVendor = existingProfile.getVendor();
        return existingVendor;
    }


    public VendorDTO getVendor(Long profile_id) {
        return modelMapper.map(getVendorFromprofileId(profile_id), VendorDTO.class);
    }

  
    public VendorDTO updateVendor(Long profile_id, VendorDTO spDTO) {
        Vendor existingVendor = getVendorFromprofileId(profile_id);
        if (existingVendor != null) {
            modelMapper.map(spDTO, existingVendor);
            return modelMapper.map(vendorRepository.save(existingVendor), VendorDTO.class);
        }
        return null;
    }





    //public vendor findByLocation() {

    //}

      // public vendorDTO getvendor(Long profile_id) {
    //     profile existingprofile = profileRepository.findById(profile_id).orElse(null);
    //     if (existingprofile == null) {
    //         return null;
    //     }
    //     vendor existingSP = existingprofile.getvendor();
   
    // }
    
}
