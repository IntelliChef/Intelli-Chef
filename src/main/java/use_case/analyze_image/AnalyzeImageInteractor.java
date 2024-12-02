package use_case.analyze_image;

import java.util.ArrayList;
import java.util.List;

import data_access.AIClient;
import entities.Ingredient;

/**
 * Interactor that calls the API and converts the resulting string output into list of ingredients.
 */
public class AnalyzeImageInteractor {
    private final AIClient AIClient;

    public AnalyzeImageInteractor(AIClient AIClient) {
        this.AIClient = AIClient;
    }

    /**
     * Interactor will make call to API from input data and convert it to output data.
     * @param analyzeImageInputData passed from controller
     * @return analyzeImageOutputData back to the controller
     * @throws RuntimeException if API call wasn't successful
     */
    public AnalyzeImageOutputData execute(AnalyzeImageInputData analyzeImageInputData) throws RuntimeException {
        String response = AIClient.analyzeImage(analyzeImageInputData.getImageBytes());
        if (response.equals("[]")) { return new AnalyzeImageOutputData(new ArrayList<>()); }
        return new AnalyzeImageOutputData(parseIngredientsToList(response));
    }

    /**
     * Converts the raw text output from the API to initialize a list of ingredients.
     * @param response raw text output
     * @return list of ingredients
     */
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
        } return ingredients;
    }
}
