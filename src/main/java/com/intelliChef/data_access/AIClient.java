package com.intelliChef.data_access;

/**
 * Interface to separate data access layer from implementation.
 */
public interface AIClient {
    /**
     * API call to Gemini with input of imageBytes.
     * @param imageBytes converted using the utility function
     * @return a basic text response that needs to be parsed to create ingredient
     * @throws RuntimeException when the API call fails
     */
    public String analyzeImage(byte[] imageBytes) throws RuntimeException;
}
