package com.supine.project_backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supine.project_backend.dto.VendorDTO;
import com.supine.project_backend.model.Vendor;
import com.supine.project_backend.model.User;
import com.supine.project_backend.repository.VendorRepository;
import com.supine.project_backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class VendorService {
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    private Vendor getVendorFromUserId(Long user_id) {
        User existingUser = userRepository.findById(user_id).orElse(null);

        if(existingUser == null) {
            return null;
        }

        // vendor existingSP = vendorRepository.findById(existinguser.getvendor().getId()).orElse(null);
        Vendor existingVendor = existingUser.getVendor();
        return existingVendor;
    }


    public VendorDTO getVendor(Long user_id) {
        Vendor existingVendor = getVendorFromUserId(user_id);
        if (existingVendor != null) {
            return modelMapper.map(existingVendor, VendorDTO.class);
        }
        return null;
    }

  
    public VendorDTO updateVendor(Long user_id, VendorDTO spDTO) {
        Vendor existingVendor = getVendorFromUserId(user_id);
        if (existingVendor != null) {
            modelMapper.map(spDTO, existingVendor);
            return modelMapper.map(vendorRepository.save(existingVendor), VendorDTO.class);
        }
        return null;
    }





    //public vendor findByLocation() {

    //}

      // public vendorDTO getvendor(Long user_id) {
    //     user existinguser = userRepository.findById(user_id).orElse(null);
    //     if (existinguser == null) {
    //         return null;
    //     }
    //     vendor existingSP = existinguser.getvendor();
   
    // }
    
}
