package com.intelliChef.adapters;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UploadImageController {

    private final AnalyzeImageInteractor analyzeImageInteractor;

    public UploadImageController(AnalyzeImageInteractor analyzeImageInteractor) {
        this.analyzeImageInteractor = analyzeImageInteractor;
    }

    public List<Ingredient> execute(String imagePath) throws IOException {
        byte[] imgBytes = getImageBytes(imagePath);
        return analyzeImageInteractor.execute(imgBytes);
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
