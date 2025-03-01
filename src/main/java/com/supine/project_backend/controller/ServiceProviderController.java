package com.supine.project_backend.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.supine.project_backend.service.ServiceProviderService;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.validation.Valid;

import com.supine.project_backend.dto.ServiceProviderDTO;
import com.supine.project_backend.model.Profile;


@RestController
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;


    @GetMapping("/api/providers/me")
    public ResponseEntity<ServiceProviderDTO> getMe() {
        //TODO - Along with other "me" APIs - Might only be Get
        return null;
    }

    @GetMapping("/api/providers/{profile_id}")
    public ResponseEntity<ServiceProviderDTO> getServiceProvider(@PathVariable Long profile_id) {
        //if null return null
        ServiceProviderDTO res = serviceProviderService.getServiceProvider(profile_id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/providers/{profile_id}")
    public ResponseEntity<ServiceProviderDTO> updateServiceProvider(@PathVariable Long profile_id, @Valid @RequestBody ServiceProviderDTO spDTO) {
        ServiceProviderDTO res = serviceProviderService.updateServiceProvider(profile_id, spDTO);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }


}


    // @PostMapping("/api/service-providers/create-sp/{profile_id}")
    // public ServiceProvider createServiceProvider(@PathVariable Long profile_id, @Valid @RequestBody ServiceProvider sp) {
    //     return serviceProviderService.createServiceProvider(profile_id, sp);
    // }

    //will need to seperate /api/sp/me and /api/profiles/get-sp/{id} using auth first and path var second