package com.intelliChef.use_case.add_ingredient;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputBoundary;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputData;

import java.util.List;

public class AddIngredientInteractor {
    private final IngredientRepository repository;
    private final GetIngredientListOutputBoundary outputBoundary;// Presenter Interface

    public AddIngredientInteractor(IngredientRepository repository, GetIngredientListOutputBoundary outputBoundary) {
        this.repository = repository;
        this.outputBoundary = outputBoundary;
    }

    public void execute(AddIngredientInputData inputData) {
        // Add data to the repository
        Ingredient ingredient = inputData.getIngredient();
        ingredient.setId(repository.getNextId());
        repository.addIngredient(ingredient);

        List<Ingredient> ingredients = repository.getAllIngredients();
        // Create output data and pass it to the presenter
        GetIngredientListOutputData outputData = new GetIngredientListOutputData(ingredients);
        outputBoundary.present(outputData);
    }
}
