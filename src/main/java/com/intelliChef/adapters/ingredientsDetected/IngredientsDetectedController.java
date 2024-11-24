package com.intelliChef.adapters;

import com.intelliChef.Main;
import com.intelliChef.entities.Ingredient;

import java.util.List;

public class IngredientsDetectedController {
    public void continueButtonClick(List<Ingredient> ingredientList,
                                    IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.continueButtonClick();
        Main.showIngredientListView(ingredientList);
    }

    public void cancelButtonClick(IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.cancelButtonClick();
        Main.showRecipeUploadView();
    }
}
