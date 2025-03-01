package com.supine.project_backend.model;


import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//refactor to vendor

@Entity
@Table(name = "service_providers")
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    private Portfolio portfolio;

    @JsonBackReference
    @OneToOne(mappedBy = "serviceProvider")
    private User user;

    @Column(nullable = true)
    private String businessName;

    private String biography;

    private String certifications;

    private boolean backgroundCheck;

     

    //Add Range for Providers - Default 100km...
    private Integer serviceRange;

    private String availability;

    private LocalDateTime lastActive;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;



    //getters

    public Long getId() {
        return id;
    }                   

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public User getUser() {
        return user;
    }
    
    public String getBusinessName() {
        return businessName;
    }

    public String getBiography() {
        return biography;
    }

    public String getCertifications() {
        return certifications;
    }

    public boolean isBackgroundCheck() {
        return backgroundCheck;
    }


    public String getAvailability() {
        return availability;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Integer getServiceRange() {
        return serviceRange;
    }

    //setters

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void setUser(User user) {
        this.user = user;
    }   

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }   

    public void setBiography(String biography) {
        this.biography = biography;
    }          

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }   

    public void setBackgroundCheck(boolean backgroundCheck) {
        this.backgroundCheck = backgroundCheck;
    }   
 

    public void setAvailability(String availability) {
        this.availability = availability;
    }   

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }   
    

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setServiceRange(Integer serviceRange) {
        this.serviceRange = serviceRange;
    }
    
}
