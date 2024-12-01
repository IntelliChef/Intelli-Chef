package com.intelliChef.use_case.add_ingredient;

import com.intelliChef.entities.Ingredient;

/**
 * The input data for the Add Ingredient Use Case.
 */
public class AddIngredientInputData {
    private final Ingredient ingredient;

    public AddIngredientInputData(String name, String quantity) {
        double quantityDouble = Double.parseDouble(quantity);
        this.ingredient = new Ingredient(name, quantityDouble);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
