package data_access;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

/**
 * Vertex API call class that will take an image of fridge and return the ingredients in the fridge along with their
 * quantities.
 */
public class GeminiAIClient implements AIClient {
    private final String projectId;
    private final String location;
    private final String modelName;

    public GeminiAIClient(String projectId, String location, String modelName) {
        this.projectId = projectId;
        this.location = location;
        this.modelName = modelName;
    }

    public String analyzeImage(byte[] imageBytes) throws RuntimeException {
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(
                    ContentMaker.fromMultiModalData(
                            PartMaker.fromMimeTypeAndData("image/png", imageBytes),
                            "Make sure not to write anything else in your response. Give me ingredients in this" +
                                    " picture of fridge in the following format separated by commas:" +
                                    " ingredient(String):quantity(integer)" +
                                    " If there are no ingredients in this picture, return an empty list only: []"
                    ));
            return ResponseHandler.getText(response);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
