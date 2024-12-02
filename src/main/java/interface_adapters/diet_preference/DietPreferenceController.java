package interface_adapters.diet_preference;

import entities.DietPreference;
import interfaces.FileStorage;
import use_case.IngredientRepository;
import use_case.diet_preference.RecipeUseCase;
import view.NavigationCall;

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
