package com.intelliChef.interfaces;

import com.intelliChef.entities.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONRecipeGateway implements RecipeGateway {

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
