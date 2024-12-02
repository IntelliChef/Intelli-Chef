package com.intelliChef.use_case.edit_ingredient;

import com.intelliChef.use_case.IngredientListRepositoryInterface;

/**
 * The Edit Ingredient Interactor
 */
public class EditIngredientInteractor implements EditIngredientInputBoundary {
    IngredientListRepositoryInterface myRepository;

    public EditIngredientInteractor(IngredientListRepositoryInterface repository) {
        myRepository = repository;
    }

    /**
     * Executes the Edit Ingredient Use Case based on the input data
     * @param editIngredientInputData input data for the Edit Ingredient Use Case
     */
    @Override
    public void execute(EditIngredientInputData editIngredientInputData) {
        int id = editIngredientInputData.getId();
        double quantity = editIngredientInputData.getQuantity();
        myRepository.updateQuantity(id, quantity);
    }
}
