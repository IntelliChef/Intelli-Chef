package com.intelliChef.use_case.ports.output;

import com.intelliChef.entities.Recipe;
import java.util.List;

/**
 * Port for accessing recipe data.
 * This interface defines how the use cases will access recipe data.
 * Following Clean Architecture, this interface is in the use cases layer
 * and will be implemented by the interface adapters layer.
 */
public interface RecipeRepository {
    /**
     * Fetches all available recipes.
     *
     * @return a list of Recipe entities
     */
    List<Recipe> fetchRecipes();
}