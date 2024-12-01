package com.intelliChef.use_case.dietPreference;


import com.intelliChef.use_case.dietPreference.RecipieOutputBoundary;

import java.io.FileWriter;
import java.io.IOException;

public class FileStorage extends RecipieOutputBoundary {
    private static final String OUTPUT_FILE = "Recipes.json";

    @Override
    public void saveToFile(String jsonResponse) throws IOException {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            writer.write(jsonResponse);
        }
    }
}
