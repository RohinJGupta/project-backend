package com.supine.project_backend.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.awt.PointShapeFactory.Point;

//refactor to profile

@Entity
@Table(name = "users")
public class User  {
    @Id
    private Long id;

    private String firstName;
    private String lastName;

    @CreationTimestamp
    private Instant createdAt;
    
    @UpdateTimestamp
    private Instant updatedAt;


    private boolean isProvider;
    private boolean hasOboarded = false;

    private Point location;
    

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
    @JoinColumn(name = "service_provider_id", referencedColumnName = "id")
    private ServiceProvider serviceProvider;
    


    //getters

    public Long getId() {
        return id;
    }
    

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }   

    public Instant getUpdatedAt() {
        return updatedAt;
    }


    public boolean isProvider() {
        return isProvider;
    }   

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public Point getLocation() {
        return location;
    }

    public boolean getHasOnboarded() {
        return hasOboarded;
    }




 

    //setters



    public void setId(Long id) {
        this.id = id;
    }         

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }   

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }   

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }    

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
 

    public void setIsProvider(boolean isProvider) {
        this.isProvider = isProvider;
    }   

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }   

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setHasOnboarded(boolean hasOboarded) {
        this.hasOboarded = hasOboarded;
    }
 

}
