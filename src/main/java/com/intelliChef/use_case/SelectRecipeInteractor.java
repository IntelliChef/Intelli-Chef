package com.intelliChef.use_case;

import com.intelliChef.use_case.dto.RecipeDTO;
import com.intelliChef.use_case.ports.input.SelectRecipeUseCase;
import com.intelliChef.use_case.ports.output.SelectRecipeOutputPort;

/**
 * Implementation of the recipe selection use case.
 * This class handles the selection of a recipe and delegates
 * the presentation to the output port.
 */
public class SelectRecipeInteractor implements SelectRecipeUseCase {
    private final SelectRecipeOutputPort outputPort;

    /**
     * Constructs a new SelectRecipeInteractor.
     *
     * @param outputPort the port through which to present the selected recipe
     */
    public SelectRecipeInteractor(SelectRecipeOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    /**
     * Handles the selection of a recipe by delegating to the output port.
     *
     * @param recipe the selected recipe data transfer object
     */
    @Override
    public void selectRecipe(RecipeDTO recipe) {
        outputPort.presentRecipeDetails(recipe);
    }
}