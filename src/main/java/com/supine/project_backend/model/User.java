package com.supine.project_backend.model;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    private String firstName;
    private String lastName;

    @CreationTimestamp
    private Instant createdAt;
    
    @UpdateTimestamp
    private Instant updatedAt;

    private boolean isVerified;
    private boolean isProvider;

    //location in profile class
    


    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
    @JoinColumn(name = "service_provider_id", referencedColumnName = "id")
    private ServiceProvider serviceProvider;
    
    //auth

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    

    //getters

    public Long getId() {
        return id;
    }
    
    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return email;
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

    public boolean isVerified() {
        return isVerified;
    }

    public boolean isProvider() {
        return isProvider;
    }   

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public String getPassword() {
        return passwordHash;
    }




 

    //setters



    public void setId(Long id) {
        this.id = id;
    }       

    public void setPhone(String phone) {
        this.phone = phone;
    }   

    public void setEmail(String email) {
        this.email = email;
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

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }   

    public void setIsProvider(boolean isProvider) {
        this.isProvider = isProvider;
    }   

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }   

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

 

}
