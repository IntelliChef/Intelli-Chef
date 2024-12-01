package com.intelliChef.adapters.ingredient_list;

import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputBoundary;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The Presenter for the Ingredient List View
 */
public class GetIngredientListPresenter implements GetIngredientListOutputBoundary {
    private IngredientListViewModel viewModel;

    public GetIngredientListPresenter() {
    }

    /**
     * Creates an Ingredient List View Model based on the given output data
     * @param outputData a list of ingredients
     */
    @Override
    public void present(GetIngredientListOutputData outputData) {
        // Transform domain entities into a UI-friendly format
        List<String> ingredientsDisplayList = outputData.getIngredients().stream()
                .map(ingredient -> "Name: " + ingredient.getName() + ", Quantity: " + ingredient.getQuantity())
                .collect(Collectors.toList());

        // Create a View Model
        viewModel = new IngredientListViewModel(ingredientsDisplayList);
    }

    public IngredientListViewModel getViewModel() {
        return viewModel;
    }
}
