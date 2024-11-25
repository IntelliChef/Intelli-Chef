package com.intelliChef.adapters.ingredientsDetected;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.view.NavigationCall;

import java.util.List;

public class IngredientsDetectedController {
    private final NavigationCall navigationCall;

    public IngredientsDetectedController(NavigationCall navigationCall) {
        this.navigationCall = navigationCall;
    }

    public void continueButtonClick(List<Ingredient> ingredientList,
                                    IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.continueButtonClick();
        // TODO: add call to ingredient list view with ingredient list using navigationCall
    }

    public void cancelButtonClick(IngredientsDetectedPresenter ingredientsDetectedPresenter) {
        ingredientsDetectedPresenter.cancelButtonClick();
        navigationCall.navigateToRecipeUploadView();
    }
}
