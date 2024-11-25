package com.intelliChef.entities;

/**
 * Represents a recipe with details such as name, image, calories, cooking time, and URL.
 */
public class Recipe {
    private String name;
    private String imageUrl;
    private double calories;
    private int cookingTime;
    private String url;

    /**
     * Constructs a new Recipe instance.
     *
     * @param name        the name of the recipe
     * @param imageUrl    the URL of the recipe's image
     * @param calories    the number of calories in the recipe
     * @param cookingTime the cooking time in minutes
     * @param url         the URL of the recipe details
     */
    public Recipe(String name, String imageUrl, double calories, int cookingTime, String url) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.calories = calories;
        this.cookingTime = cookingTime;
        this.url = url;
    }

    /**
     * Returns the name of the recipe.
     *
     * @return the name of the recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the URL of the recipe's image.
     *
     * @return the URL of the recipe's image
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Returns the number of calories in the recipe.
     *
     * @return the number of calories
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Returns the cooking time of the recipe in minutes.
     *
     * @return the cooking time in minutes
     */
    public int getCookingTime() {
        return cookingTime;
    }

    /**
     * Returns the URL of the recipe details.
     *
     * @return the URL of the recipe details
     */
    public String getUrl() {
        return url;
    }
}
