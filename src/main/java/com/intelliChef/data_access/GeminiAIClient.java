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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Turns the image into array of bytes.
     * @param imagePath is the absolute path of the image
     * @return an array of the bytes of the image
     * @throws IOException if path is not of an image
     */
    private static byte[] getImageBytes(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);  // Turn image file to byte array
    }
}
