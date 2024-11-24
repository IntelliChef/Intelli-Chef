package com.intelliChef.use_case;

import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;

public class InteractorFactory {
    public Object create(String interactorName, GeminiAIClient geminiAIClient) {
        if (interactorName.equals("AnalyzeImage")) {
            return new AnalyzeImageInteractor(geminiAIClient);
        }
        return null;
    }
}
