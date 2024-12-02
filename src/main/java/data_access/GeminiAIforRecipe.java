package data_access;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import java.io.IOException;
import java.util.List;

/**
 * Vertex API call class that will take an image of fridge and return the ingredients in the fridge along with their
 * quantities.
 */


public class GeminiAIforRecipe{
    private final String projectId = "";
    private final String location = "us-central1";
    private final String modelName = "gemini-1.5-flash-001";

    public GeminiAIforRecipe() {
    }

    public String getRecipe(List<String> dietPreference, List<String> ingredients) throws RuntimeException {

        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent("Give me a list of 10 recipies that i can make" +
                    "using only these ingredients"
                    +ingredients.toString()+
                    "when my diet preference are as follows"+
                    dietPreference.toString()+
                    "give your response back in a JSON file, do not write any extra words or symbols. Format your json " +
                    "file according to this example. " + "Make sure to follow the JSON file structure accurately and completely" +
                    "{" +
                    "  \"hits\": [" +
                    "    {" +
                    "      \"recipe\": {" +
                    "        \"label\": \"Spaghetti Carbonara\"," +
                    "        \"image\": \"https://example.com/images/spaghetti-carbonara.jpg\"," +
                    "        \"calories\": 700.5," +
                    "        \"totalTime\": 30," +
                    "        \"url\": \"https://example.com/recipes/spaghetti-carbonara\"" +
                    "      }" +
                    "    }," +
                    "    {" +
                    "      \"recipe\": {" +
                    "        \"label\": \"Chicken Caesar Salad\"," +
                    "        \"image\": \"https://example.com/images/chicken-caesar-salad.jpg\"," +
                    "        \"calories\": 350.0," +
                    "        \"totalTime\": 20," +
                    "        \"url\": \"https://example.com/recipes/chicken-caesar-salad\"" +
                    "      }" +
                    "    }," +
                    "   ]" +
                    "}");
            return ResponseHandler.getText(response);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
