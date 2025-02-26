package com.supine.project_backend.dto;


import java.time.LocalDateTime;

import org.locationtech.jts.geom.Point;

public class ServiceProviderDTO {


    private Long id;
    private String businessName;
    private String biography;
    private String certifications;
    private boolean backgroundCheck;
    private Point location;
    private String availability;
    private LocalDateTime lastActive;


    // Getters and Setters



    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public boolean isBackgroundCheck() {
        return backgroundCheck;
    }

    public void setBackgroundCheck(boolean backgroundCheck) {
        this.backgroundCheck = backgroundCheck;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }


} 