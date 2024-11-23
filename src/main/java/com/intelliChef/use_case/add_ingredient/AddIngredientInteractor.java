package com.intelliChef.use_case.add_ingredient;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;

public class AddIngredientInteractor {
    private IngredientRepository repository;
    public AddIngredientInteractor(IngredientRepository repository) {
        this.repository = repository;
    }

    public void execute(AddIngredientInputData inputData) {
        // Add data to the repository
        Ingredient ingredient = inputData.getIngredient();
        repository.addIngredient(ingredient);

        // Create output data and pass it to the presenter
        AddIngredientOutputData outputData = new AddIngredientOutputData();
        outputBoundary.present(outputData);
    }
}
