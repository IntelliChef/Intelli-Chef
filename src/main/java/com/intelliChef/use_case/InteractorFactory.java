package com.intelliChef.use_case;

import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.use_case.ingredientDetected.IngredientsDetectedInteractor;

public class InteractorFactory {
    public Object create(String interactorName, GeminiAIClient geminiAIClient) {
        if (interactorName.equals("AnalyzeImage")) {
            return new AnalyzeImageInteractor(geminiAIClient);
        }
        else if (interactorName.equals("IngredientsDetected")) {
            return new IngredientsDetectedInteractor();
        }
        return null;
    }
}
