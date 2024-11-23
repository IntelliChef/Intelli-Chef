package com.intelliChef.use_case.add_ingredient;

import com.intelliChef.entities.Ingredient;

public class AddIngredientInputData {
    private final Ingredient ingredient;

    public AddIngredientInputData(String name, String quantity) {
        int quantityInt = Integer.parseInt(quantity);
        this.ingredient = new Ingredient(name, quantityInt);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
