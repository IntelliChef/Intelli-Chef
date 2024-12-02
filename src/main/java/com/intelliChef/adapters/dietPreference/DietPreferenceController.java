package com.intelliChef.adapters.dietPreference;

import com.intelliChef.entities.DietPreference;
import com.intelliChef.interfaces.FileStorage;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.dietPreference.RecipeUseCase;
import com.intelliChef.view.NavigationCall;

public class DietPreferenceController {
    private final NavigationCall navigationCall;


    public DietPreferenceController(NavigationCall navigationCall) {
        this.navigationCall = navigationCall;
    }

    public void fetchRecipes(DietPreference dietPreference, IngredientRepository ingredients) {
        RecipeUseCase useCase = new RecipeUseCase(new FileStorage());
        useCase.processRecipes(dietPreference, ingredients);
    }

    public void confirmClick(){
        navigationCall.navigateToRecipeView();
    }
}
