package com.intelliChef.entities;

import entities.DietPreference;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class DietPreferenceTest {

    @Test
    public void testConstructorAndGetter() {
        // Create a list of preferences
        List<String> preferences = Arrays.asList("Vegetarian", "Gluten-Free", "Dairy-Free");

        // Instantiate the DietPreference object
        DietPreference dietPreference = new DietPreference(preferences);

        // Verify that the preferences are correctly set
        assertNotNull(dietPreference.getPreferences(), "Preferences list should not be null");
        assertEquals(preferences, dietPreference.getPreferences(), "Preferences list should match the input");
    }

    @Test
    public void testEmptyPreferences() {
        // Create an empty list of preferences
        List<String> preferences = Arrays.asList();

        // Instantiate the DietPreference object
        DietPreference dietPreference = new DietPreference(preferences);

        // Verify that the preferences are correctly set
        assertNotNull(dietPreference.getPreferences(), "Preferences list should not be null");
        assertTrue(dietPreference.getPreferences().isEmpty(), "Preferences list should be empty");
    }

    @Test
    public void testImmutablePreferences() {
        // Create a list of preferences
        List<String> preferences = Arrays.asList("Vegan", "Nut-Free");

        // Instantiate the DietPreference object
        DietPreference dietPreference = new DietPreference(preferences);

        // Verify that modifying the original list does not affect the stored preferences
        preferences.set(0, "Non-Vegan");
        assertEquals(Arrays.asList("Vegan", "Nut-Free"), dietPreference.getPreferences(),
                "Modifying the original list should not change the stored preferences");
    }
}
