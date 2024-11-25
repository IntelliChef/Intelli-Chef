package com.intelliChef.adapters.ingredientsDetected;

import com.intelliChef.Main;
import com.intelliChef.entities.Ingredient;

import java.util.List;

public class IngredientsDetectedController {
    public void continueButtonClick(List<Ingredient> ingredientList,
                                    IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.continueButtonClick();
        // ViewManager.showIngredientListView();
        Main.showIngredientListView(ingredientList);
    }

    public void cancelButtonClick(IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.cancelButtonClick();
        // ViewManager.showRecipeUploadView();
        Main.showRecipeUploadView();
    }
}
