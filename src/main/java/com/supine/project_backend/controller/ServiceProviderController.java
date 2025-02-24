package com.supine.project_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PutMapping("/api/service-providers/update-sp/{user_id}")
    public ServiceProvider updateServiceProvider(@PathVariable Long user_id, @Valid @RequestBody ServiceProvider sp) {
        return serviceProviderService.updateServiceProvider(user_id, sp);
    }


}
