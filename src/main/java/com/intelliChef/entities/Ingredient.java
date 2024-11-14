package com.intelliChef.entities;

public class Ingredient {
    private String name;
    private double quantity;

    public Ingredient(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
