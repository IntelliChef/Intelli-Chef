package com.intelliChef.interfaces;

import com.intelliChef.entities.Recipe;
import java.util.List;

/**
 * Represents a gateway for loading recipe data from various sources.
 */
public interface RecipeGateway {

    /**
     * Loads a list of recipes.
     *
     * @return a list of {@link Recipe} objects
     */
    List<Recipe> loadRecipes();
}
