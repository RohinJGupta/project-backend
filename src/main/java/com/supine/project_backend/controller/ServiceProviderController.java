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

import com.supine.project_backend.model.ServiceProvider;

@RestController
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;

    // @PostMapping("/api/service-providers/create-sp/{user_id}")
    // public ServiceProvider createServiceProvider(@PathVariable Long user_id, @Valid @RequestBody ServiceProvider sp) {
    //     return serviceProviderService.createServiceProvider(user_id, sp);
    // }

        //will need to seperate /api/sp/me and /api/users/get-sp/{id} using auth first and path var second

    @GetMapping("/api/service-providers/get-sp/{user_id}")
    public ServiceProvider getServiceProvider(@PathVariable Long user_id) {
        //if null return null
        return serviceProviderService.getServiceProvider(user_id);
    }

    @PutMapping("/api/service-providers/update-sp/{user_id}")
    public ResponseEntity<Void> updateServiceProvider(@PathVariable Long user_id, @Valid @RequestBody ServiceProvider sp) {
        ServiceProvider updatedSP = serviceProviderService.updateServiceProvider(user_id, sp);

        if(updatedSP != null) {
            return ResponseEntity.status(200).build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


}
