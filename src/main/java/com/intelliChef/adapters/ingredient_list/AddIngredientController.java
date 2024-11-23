package com.intelliChef.adapters.ingredient_list;

import com.intelliChef.use_case.add_ingredient.AddIngredientInputData;
import com.intelliChef.use_case.add_ingredient.AddIngredientInteractor;

public class AddIngredientController {

    public AddIngredientController() {

    }
    public void execute(String name, String quantity) {
        final AddIngredientInputData inputData = new AddIngredientInputData(name, quantity);
        AddIngredientInteractor interactor = new AddIngredientInteractor();
        interactor.execute(inputData);
    }
}
