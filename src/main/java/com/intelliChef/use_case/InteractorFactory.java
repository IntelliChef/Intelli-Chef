package com.intelliChef.use_case;

import com.intelliChef.data_access.GeminiAIClient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;

/**
 * Factory to initialize any new interactors.
 */
public class InteractorFactory {
    public AnalyzeImageInteractor createAnalyzeImageInteractor(GeminiAIClient geminiAIClient) {
        return new AnalyzeImageInteractor(geminiAIClient);
    }
}
