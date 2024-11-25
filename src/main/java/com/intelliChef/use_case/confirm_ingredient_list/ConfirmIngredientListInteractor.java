package com.intelliChef.use_case.confirm_ingredient_list;

import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.confirm_ingredient_list.ConfirmIngredientListInputBoundary;
import com.intelliChef.use_case.confirm_ingredient_list.ConfirmIngredientListInputData;

import java.util.List;

public class ConfirmIngredientListInteractor implements ConfirmIngredientListInputBoundary {
    private final IngredientRepository repository;

    public ConfirmIngredientListInteractor(IngredientRepository repository) {
        this.repository = repository;
    }
    /**
     * Executes the Confirm Ingredient List Use Case.
     * Updates the ingredient list so that now it only contains checked ingredients.
     * @param inputData the input data
     */
    @Override
    public void execute(ConfirmIngredientListInputData inputData) {
        List<Integer> ids = inputData.getIds();
        for (Integer id : ids) {
            repository.deleteIngredient(id);
        }
    }
}
