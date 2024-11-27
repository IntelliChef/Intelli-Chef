package com.intelliChef.use_case.ports.output;

import com.intelliChef.use_case.dto.RecipeDTO;

/**
 * Output port for presenting recipe details after selection.
 * This interface defines how the use case will present the selected recipe to the outer layers.
 * Following Clean Architecture, this interface is in the use cases layer and
 * will be implemented by presenters in the interface adapters layer.
 */
public interface SelectRecipeOutputPort {
    /**
     * Presents the details of the selected recipe.
     *
     * @param recipe the recipe data transfer object to be presented
     */
    void presentRecipeDetails(RecipeDTO recipe);
}