package use_case.edit_ingredient;

/**
 * Input data for the Edit Ingredient Use Case
 * It stores id of the ingredient to be edited and quantity to be updated to.
 */
public class EditIngredientInputData {
    private int id;
    private double quantity;

    public EditIngredientInputData(int id, double quantity) {
        this.id = id;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
    public double getQuantity() {
        return quantity;
    }
}