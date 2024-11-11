package com.intelliChef.adapters;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_cases.AnalyzeImageUseCase;

import java.io.IOException;
import java.util.List;

public class UploadImageController {

    private final AnalyzeImageUseCase analyzeImageUseCase;

    public UploadImageController(AnalyzeImageUseCase analyzeImageUseCase) {
        this.analyzeImageUseCase = analyzeImageUseCase;
    }

    public List<Ingredient> execute(String imagePath) throws IOException {
        return analyzeImageUseCase.execute(imagePath);
    }
}
