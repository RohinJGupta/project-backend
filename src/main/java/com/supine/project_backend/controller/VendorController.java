package com.supine.project_backend.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.supine.project_backend.service.VendorService;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.validation.Valid;

import com.supine.project_backend.dto.VendorDTO;


@RestController
public class VendorController {
    @Autowired
    private VendorService vendorService;


    @GetMapping("/api/v1/me/providers")
    public ResponseEntity<VendorDTO> getMe() {
        //TODO - Along with other "me" APIs - Might only be Get
        return null;
    }

    @GetMapping("/api/v1/providers/{user_id}")
    public ResponseEntity<VendorDTO> getVendor(@PathVariable Long user_id) {
        //if null return null
        VendorDTO res = vendorService.getVendor(user_id);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/v1/providers/{user_id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long user_id, @Valid @RequestBody VendorDTO vDTO) {
        VendorDTO res = vendorService.updateVendor(user_id, vDTO);
        if(res != null) {
            return ResponseEntity.ok().body(res);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }


}


    // @PostMapping("/api/service-providers/create-sp/{user_id}")
    // public vendor createvendor(@PathVariable Long user_id, @Valid @RequestBody vendor sp) {
    //     return vendorService.createvendor(user_id, sp);
    // }

    //will need to seperate /api/sp/me and /api/users/get-sp/{id} using auth first and path var second