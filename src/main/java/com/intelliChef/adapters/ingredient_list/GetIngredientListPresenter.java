package com.intelliChef.adapters.ingredient_list;

import com.intelliChef.adapters.ingredient_list.IngredientListViewModel;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputBoundary;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListOutputData;

import java.util.List;
import java.util.stream.Collectors;

public class GetIngredientListPresenter implements GetIngredientListOutputBoundary {
    private IngredientListViewModel viewModel;

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
