package com.supine.project_backend.model;


import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "portfolio_items")
public class PortfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    private String title;

    private String description;

    private float projectCost;

    private int duration;

    private String category;

    private boolean isFeatured;

    private LocalDate projectDate;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
    

    //TODO: Add Support for Media

    //getters

    public Long getId() {
        return id;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getProjectCost() {
        return projectCost;
    }

    public int getDuration() {
        return duration;
    }

    public String getCategory() {
        return category;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public LocalDate getProjectDate() {
        return projectDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProjectCost(float projectCost) {
        this.projectCost = projectCost;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCategory(String category) {
        this.category = category;   
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public void setProjectDate(LocalDate projectDate) {
        this.projectDate = projectDate;
    }
    
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }            
    

    
}
