package use_case.diet_preference;

import data_access.GeminiAIforRecipe;
import entities.DietPreference;
import entities.Ingredient;
import interfaces.FileStorage;
import use_case.IngredientRepository;

import java.util.ArrayList;
import java.util.List;

public class RecipeUseCase {
    private GeminiAIforRecipe geminiService = new GeminiAIforRecipe();
    private FileStorage fileStorage;

    public RecipeUseCase(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public void processRecipes(DietPreference preferences, IngredientRepository ingredients) {
        List<Ingredient> listOfIngredients = ingredients.getAllIngredients();
        List<String> stringifiedIngredients= new ArrayList<>();
        for (Ingredient ingredient : listOfIngredients) {
            stringifiedIngredients.add(ingredient.getName() + "  " + ingredient.getQuantity());
        }

        try {
            String jsonResponse = geminiService.getRecipe(preferences.getPreferences(), stringifiedIngredients);
            fileStorage.saveToFile(jsonResponse);
        } catch (Exception e) {
            System.err.println("Error processing recipes: " + e.getMessage());
        }
    }
}
