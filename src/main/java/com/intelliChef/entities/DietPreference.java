package com.intelliChef.entities;

import java.util.List;

public class DietPreference {
    private List<String> preferences;

    public DietPreference(List<String> preferences) {
        this.preferences = preferences;
    }

    public List<String> getPreferences() {
        return preferences;
    }
}
