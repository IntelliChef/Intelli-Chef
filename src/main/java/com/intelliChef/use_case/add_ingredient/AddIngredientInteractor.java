package com.intelliChef.use_case.add_ingredient;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputBoundary;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputData;

import java.util.List;

public class AddIngredientInteractor {
    private final IngredientRepository repository;

    public AddIngredientInteractor(IngredientRepository repository) {
        this.repository = repository;
    }

    public void execute(AddIngredientInputData inputData) {
        // Add data to the repository
        Ingredient ingredient = inputData.getIngredient();
        ingredient.setId(repository.getNextId());
        repository.addIngredient(ingredient);
    }
}
