package com.supine.project_backend.dto;


import java.time.LocalDate;

public class NewPortfolioItemDTO {


    private String title;
    private String description;
    private float projectCost;
    private int duration;
    private String category;
    private boolean isFeatured;
    private LocalDate projectDate;


    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(float projectCost) {
        this.projectCost = projectCost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public LocalDate getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(LocalDate projectDate) {
        this.projectDate = projectDate;
    }

} 