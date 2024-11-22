package com.intelliChef.use_case.analyzeImage;

import java.io.IOException;
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
        return new AnalyzeImageOutputData(parseIngredients(response));
    }

    /**
     * Takes an input String and converts it into a list of ingredients.
     * @param response has the format ingredient:quantity separated by commas
     * @return a list of type Ingredient in the response
     */
    private List<Ingredient> parseIngredients(String response) {
        List<Ingredient> ingredients = new ArrayList<>();
        String[] pairs = response.split(", ");

        for (String pair : pairs) {
            String[] parts = pair.split(":");
            String name = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());
            ingredients.add(new Ingredient(name, quantity));
        }
        return ingredients;
    }
}
