package com.intelliChef.use_case.get_ingredient_list;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;

import java.util.List;

public class GetIngredientListInteractor implements GetIngredientListInputBoundary {
    private final IngredientRepository ingredientRepository; // Data Access Interface
    private final GetIngredientListOutputBoundary outputBoundary; // Presenter Interface

    public GetIngredientListInteractor(IngredientRepository ingredientRepository,
                                       GetIngredientListOutputBoundary outputBoundary) {
        this.ingredientRepository = ingredientRepository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(GetIngredientListInputData inputData) {
        // Fetch data from the repository
        List<Ingredient> ingredients = ingredientRepository.getAllIngredients();

        // Create output data and pass it to the presenter
        GetIngredientListOutputData outputData = new GetIngredientListOutputData(ingredients);
        outputBoundary.present(outputData);
    }
}

