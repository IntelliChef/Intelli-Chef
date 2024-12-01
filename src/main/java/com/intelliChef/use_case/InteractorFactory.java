package com.intelliChef.use_case;

import com.intelliChef.data_access.AIClient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;

/**
 * Factory to initialize any new interactors.
 */
public class InteractorFactory {
    public AnalyzeImageInteractor createAnalyzeImageInteractor(AIClient AIClient) {
        return new AnalyzeImageInteractor(AIClient);
    }
}
