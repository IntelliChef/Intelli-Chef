package com.intelliChef.data_access;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Vertex API call class that will take an image of fridge and return the ingredients in the fridge along with their
 * quantities.
 */
public class GeminiAIClient {
    private final String projectId;
    private final String location;
    private final String modelName;

    public GeminiAIClient(String projectId, String location, String modelName) {
        this.projectId = projectId;
        this.location = location;
        this.modelName = modelName;
    }

    public String analyzeImage(String imagePath) throws IOException {
        byte[] imageBytes = getImageBytes(imagePath);

        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(
                    ContentMaker.fromMultiModalData(
                            PartMaker.fromMimeTypeAndData("image/png", imageBytes),
                            "Make sure not to write anything else in your response. Give me ingredients in this fridge" +
                                    " in the following format separated by commas: ingredient(String):quantity(integer)"
                    ));
            return ResponseHandler.getText(response);
        }
    }

    private static byte[] getImageBytes(String imagePath) throws IOException {
        // Get path of the image
        Path path = Paths.get(imagePath);
        // Turn image file to byte array
        return Files.readAllBytes(path);
    }
}
