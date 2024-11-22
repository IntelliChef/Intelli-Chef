package com.intelliChef.adapters;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInputData;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Controller that takes path of image and turns the image into bytes so it can be passed into the interactor.
 */
public class UploadImageController {

    private final AnalyzeImageInteractor analyzeImageInteractor;

    public UploadImageController(AnalyzeImageInteractor analyzeImageInteractor) {
        this.analyzeImageInteractor = analyzeImageInteractor;
    }

    public List<Ingredient> execute(String imagePath) throws RuntimeException, IOException {
        byte[] imgBytes = getImageBytes(imagePath);
        return analyzeImageInteractor.execute(new AnalyzeImageInputData(imgBytes)).getIngredientList();
    }

    /**
     * Turns the image into array of bytes.
     * @param imagePath is the absolute path of the image
     * @return an array of the bytes of the image
     * @throws RuntimeException if path is not of an image
     */
    private static byte[] getImageBytes(String imagePath) throws RuntimeException, IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);  // Turn image file to byte array
    }
}
