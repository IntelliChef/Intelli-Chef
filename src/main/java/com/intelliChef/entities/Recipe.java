package com.intelliChef.entities;

public class Recipe {
    private String name;
    private String imageUrl;
    private double calories;
    private int cookingTime;
    private String url;

    public Recipe(String name, String imageUrl, double calories, int cookingTime, String url) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.calories = calories;
        this.cookingTime = cookingTime;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getCalories() {
        return calories;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public String getUrl() {
        return url;
    }
}