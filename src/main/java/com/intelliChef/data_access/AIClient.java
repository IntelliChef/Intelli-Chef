package com.intelliChef.data_access;

/**
 * Interface to separate logic of API call from its implementation.
 */
public interface AIClient {

    /**
     * API call takes place with input of imageBytes.
     * @param imageBytes converted using the utility function
     * @return a basic text response that needs to be parsed to create ingredient
     * @throws RuntimeException when the API call fails
     */
    String analyzeImage(byte[] imageBytes) throws RuntimeException;
    }
