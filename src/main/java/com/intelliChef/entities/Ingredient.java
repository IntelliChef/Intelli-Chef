package com.intelliChef.entities;

public class Ingredient {
    private int id;
    private String name;
    private double quantity;
    private boolean selected;

    public Ingredient(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
        this.selected = true;
    }

    public int getId() { return id;}

    public void setId(int id) { this.id = id; }

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

    public boolean isSelected() { return this.selected; }

    public void setSelected(boolean selected) { this.selected = selected; }
}
