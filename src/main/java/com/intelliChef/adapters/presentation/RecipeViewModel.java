package com.intelliChef.adapters.presentation;

/**
 * View model class for recipe presentation.
 * This class contains formatted data ready for display in the UI.
 * Following Clean Architecture, this class is in the interface adapters layer
 * and contains only presentation-ready data.
 */
public class RecipeViewModel {
    private final String displayName;
    private final String imageUrl;
    private final String formattedCalories;
    private final String formattedTime;
    private final String url;

    /**
     * Constructs a new RecipeViewModel.
     *
     * @param displayName       the formatted name of the recipe
     * @param imageUrl         the URL of the recipe's image
     * @param formattedCalories the formatted calorie count (e.g., "500 calories")
     * @param formattedTime    the formatted cooking time (e.g., "30 mins")
     * @param url             the URL of the recipe details
     */
    public RecipeViewModel(String displayName, String imageUrl, String formattedCalories,
                           String formattedTime, String url) {
        this.displayName = displayName;
        this.imageUrl = imageUrl;
        this.formattedCalories = formattedCalories;
        this.formattedTime = formattedTime;
        this.url = url;
    }

    /**
     * Returns the formatted display name of the recipe.
     *
     * @return the recipe's display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the URL of the recipe's image.
     *
     * @return the image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Returns the formatted calorie count.
     *
     * @return the formatted calories (e.g., "500 calories")
     */
    public String getFormattedCalories() {
        return formattedCalories;
    }

    /**
     * Returns the formatted cooking time.
     *
     * @return the formatted time (e.g., "30 mins")
     */
    public String getFormattedTime() {
        return formattedTime;
    }

    /**
     * Returns the URL of the recipe details.
     *
     * @return the recipe URL
     */
    public String getUrl() {
        return url;
    }
}