package frameworks.factories;

import data_access.AIClient;
import use_case.analyze_image.AnalyzeImageInteractor;

/**
 * Factory to initialize any new interactors.
 */
public class InteractorFactory {
    public AnalyzeImageInteractor createAnalyzeImageInteractor(AIClient AIClient) {
        return new AnalyzeImageInteractor(AIClient);
    }
}
