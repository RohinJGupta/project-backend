package com.supine.project_backend.model;


import java.sql.Date;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import java.util.*;



@Entity
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String title;

    private String overview;

    private Date createdAt;

    private Date updatedAt;

    @JsonBackReference
    @OneToOne(mappedBy = "portfolio")
    private ServiceProvider serviceProvider;

    @JsonManagedReference
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PortfolioItem> items;





    //getters

    public Long getId() {
        return id;
    }       

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }   

    public List<PortfolioItem> getItems() {
        return items;
    }   

    public String getTitle() {
        return title;
    }   

    public String getOverview() {
        return overview;
    }   

    public Date getCreatedAt() {
        return createdAt;
    }   
    
    public Date getUpdatedAt() {
        return updatedAt;
    }   

    //setters   

    public void setId(Long id) {
        this.id = id;
    }      

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }                      

    public void setItems(ArrayList<PortfolioItem> items) {
        this.items = items;
    }   

    public void setTitle(String title) {
        this.title = title;
    }   

    public void setOverview(String overview) {
        this.overview = overview;
    }   

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }   

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }              
    
    
}
