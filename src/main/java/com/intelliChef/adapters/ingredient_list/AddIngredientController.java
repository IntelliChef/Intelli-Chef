package com.intelliChef.adapters.ingredient_list;

import com.intelliChef.use_case.add_ingredient.AddIngredientInputData;
import com.intelliChef.use_case.add_ingredient.AddIngredientInteractor;

public class AddIngredientController {
    private final AddIngredientInteractor interactor;

    public AddIngredientController(AddIngredientInteractor interactor) {
        this.interactor = interactor;
    }
    public void execute(String name, String quantity) {
        final AddIngredientInputData inputData = new AddIngredientInputData(name, quantity);
        interactor.execute(inputData);
    }
}
