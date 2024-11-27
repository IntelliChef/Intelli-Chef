package com.intelliChef.adapters.persistence;

import com.intelliChef.entities.Recipe;
import com.intelliChef.use_case.ports.output.RecipeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON-based implementation of the RecipeRepository interface.
 * This class handles loading recipe data from a JSON file.
 * Following Clean Architecture, this class is in the interface adapters layer
 * and implements a port from the use case layer.
 */
public class JSONRecipeRepository implements RecipeRepository {

    /**
     * Loads recipes from a JSON file.
     *
     * @return a list of Recipe entities
     * @throws RuntimeException if the JSON file cannot be found or fails to load
     */
    @Override
    public List<Recipe> fetchRecipes() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("recipes.json")) {
            if (is == null) {
                throw new RuntimeException("File not found: recipes.json");
            }

            JSONObject jsonObject = new JSONObject(new JSONTokener(is));
            JSONArray hitsArray = jsonObject.getJSONArray("hits");
            List<Recipe> recipes = new ArrayList<>();

            for (int i = 0; i < hitsArray.length(); i++) {
                JSONObject recipeJson = hitsArray.getJSONObject(i).getJSONObject("recipe");
                recipes.add(createRecipeFromJSON(recipeJson));
            }

            return recipes;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load recipes", e);
        }
    }

    /**
     * Creates a Recipe entity from a JSON object.
     *
     * @param recipeJson the JSON object containing recipe data
     * @return a Recipe entity
     */
    private Recipe createRecipeFromJSON(JSONObject recipeJson) {
        return new Recipe(
                recipeJson.getString("label"),
                recipeJson.optString("image", ""),
                recipeJson.optDouble("calories", 0.0),
                recipeJson.optInt("totalTime", -1),
                recipeJson.optString("url", "")
        );
    }
}