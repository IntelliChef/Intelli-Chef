package com.intelliChef.interfaces;

import com.intelliChef.entities.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A JSON-based implementation of the {@link RecipeGateway} interface.
 * This class loads recipe data from a JSON file and converts it into a list of {@link Recipe} objects.
 */
public class JSONRecipeGateway implements RecipeGateway {

    /**
     * Loads recipes from a JSON file located in the classpath.
     *
     * @return a list of {@link Recipe} objects parsed from the JSON file
     * @throws RuntimeException if the JSON file cannot be found or fails to load
     */
    @Override
    public List<Recipe> loadRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("recipes.json")) {
            if (is == null) {
                throw new RuntimeException("File not found: recipes.json");
            }
            JSONObject jsonObject = new JSONObject(new JSONTokener(is));
            JSONArray hitsArray = jsonObject.getJSONArray("hits");
            for (int i = 0; i < hitsArray.length(); i++) {
                JSONObject recipeJson = hitsArray.getJSONObject(i).getJSONObject("recipe");
                String name = recipeJson.getString("label");
                String imageUrl = recipeJson.optString("image", "");
                double calories = recipeJson.optDouble("calories", 0.0);
                int cookingTime = recipeJson.optInt("totalTime", -1);
                String url = recipeJson.optString("url", "");
                recipes.add(new Recipe(name, imageUrl, calories, cookingTime, url));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load recipes", e);
        }
        return recipes;
    }
}
