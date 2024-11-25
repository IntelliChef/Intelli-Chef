package com.intelliChef.use_case.analyzeImage;

import java.util.ArrayList;
import java.util.List;

import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.entities.Ingredient;

/**
 * Interactor that calls the API and converts the resulting string output into list of ingredients.
 */
public class AnalyzeImageInteractor {
    private final GeminiAIClient geminiAIClient;

    public AnalyzeImageInteractor(GeminiAIClient geminiAIClient) {
        this.geminiAIClient = geminiAIClient;
    }

    public AnalyzeImageOutputData execute(AnalyzeImageInputData analyzeImageInputData) throws RuntimeException {
        String response = geminiAIClient.analyzeImage(analyzeImageInputData.getImageBytes());
        if (response.equals("[]")) {
            return new AnalyzeImageOutputData(new ArrayList<>());
        }
        return new AnalyzeImageOutputData(parseIngredientsToList(response));
    }

    private List<Ingredient> parseIngredientsToList(String response) {
        List<Ingredient> ingredients = new ArrayList<>();
        String[] pairs = response.split(", ");
        int i = 0;

        for (String pair : pairs) {
            String[] parts = pair.split(":");

            String name = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());

            Ingredient ing = new Ingredient(name, quantity);
            ing.setId(i);
            ingredients.add(ing);
            i++;
        }
        return ingredients;
    }
}
