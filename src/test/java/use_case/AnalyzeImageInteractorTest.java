package use_case;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.AIClient;
import use_case.analyze_image.AnalyzeImageInputData;
import use_case.analyze_image.AnalyzeImageInteractor;
import use_case.analyze_image.AnalyzeImageOutputData;

public class AnalyzeImageInteractorTest {
    @Test
    public void testOutputWithValidResponse() {
        byte[] imageBytes = "test image".getBytes();
        String mockResponse = "Tomato:2, Onion:1";
        AIClient mockAIClient = mock(AIClient.class);
        when(mockAIClient.analyzeImage(imageBytes)).thenReturn(mockResponse);
        AnalyzeImageInputData inputData = new AnalyzeImageInputData(imageBytes);
        AnalyzeImageInteractor interactor = new AnalyzeImageInteractor(mockAIClient);

        AnalyzeImageOutputData outputData = interactor.execute(inputData);

        assertNotNull(outputData.getIngredientList());
        assertEquals(2, outputData.getIngredientList().size());
        assertEquals("Tomato", outputData.getIngredientList().get(0).getName());
    }

    @Test
    public void testOutputWithEmptyResponse() {
        byte[] imageBytes = "test image".getBytes();
        String mockResponse = "[]";
        AIClient mockAIClient = mock(AIClient.class);
        when(mockAIClient.analyzeImage(imageBytes)).thenReturn(mockResponse);

        AnalyzeImageInputData inputData = new AnalyzeImageInputData(imageBytes);
        AnalyzeImageInteractor interactor = new AnalyzeImageInteractor(mockAIClient);

        AnalyzeImageOutputData outputData = interactor.execute(inputData);

        assertTrue(outputData.isEmpty());
    }

    @Test
    public void testExceptionForInvalidResponse() {
        byte[] imageBytes = "test image".getBytes();
        AIClient mockAIClient = mock(AIClient.class);
        when(mockAIClient.analyzeImage(imageBytes)).thenThrow(new RuntimeException("API error"));

        AnalyzeImageInputData inputData = new AnalyzeImageInputData(imageBytes);
        AnalyzeImageInteractor interactor = new AnalyzeImageInteractor(mockAIClient);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> interactor.execute(inputData));
        assertEquals("API error", exception.getMessage());
    }
}
