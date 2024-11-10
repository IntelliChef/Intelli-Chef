package com.intelliChef.entities;

public class Ingredient {
    private final String name;
    private final int quantity;

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
