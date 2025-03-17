package com.supine.project_backend.dto;

import org.locationtech.jts.geom.Point;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {

    private Long id;
    private String phone;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isVerified;
    private boolean isProvider;
    private boolean hasOboarded;
    private Point location;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public boolean isProvider() {
        return isProvider;
    }

    public void setIsProvider(boolean isProvider) {
        this.isProvider = isProvider;
    }
} 