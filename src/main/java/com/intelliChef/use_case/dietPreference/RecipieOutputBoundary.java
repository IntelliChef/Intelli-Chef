package com.intelliChef.use_case.dietPreference;

public abstract class RecipieOutputBoundary {
    public abstract void saveToFile(String jsonResponse) throws Exception;
}