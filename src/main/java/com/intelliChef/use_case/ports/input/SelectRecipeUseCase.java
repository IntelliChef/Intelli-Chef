package com.intelliChef.use_case.ports.input;

import com.intelliChef.use_case.dto.RecipeDTO;

/**
 * Input port for recipe selection.
 * This interface defines the use case of selecting a recipe in the application.
 * Following Clean Architecture, this interface is in the use cases layer and
 * will be called by the outer layers (adapters/UI) and implemented by an interactor.
 */
public interface SelectRecipeUseCase {
    /**
     * Handles the selection of a recipe.
     * The actual implementation will process the selection and present
     * the recipe details through the output port.
     *
     * @param recipe the selected recipe data transfer object
     */
    void selectRecipe(RecipeDTO recipe);
}