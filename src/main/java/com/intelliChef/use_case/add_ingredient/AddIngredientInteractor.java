package com.intelliChef.use_case.add_ingredient;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;

/**
 * The Add Ingredient Interactor
 */
public class AddIngredientInteractor {
    private final IngredientRepository repository;

    public AddIngredientInteractor(IngredientRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the Change Password Use Case so that given ingredients are added to the DAO.
     */
    public void execute(AddIngredientInputData inputData) {
        // Add data to the repository
        Ingredient ingredient = inputData.getIngredient();
        ingredient.setId(repository.getNextId());
        repository.addIngredient(ingredient);
    }
}
