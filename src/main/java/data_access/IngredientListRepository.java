package data_access;

import entities.Ingredient;
import use_case.IngredientRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Data storage object that will store all the ingredients entered by the user.
 */
public class IngredientListRepository implements IngredientRepository {
    private static final List<Ingredient> myIngredients = new ArrayList<Ingredient>();

    public IngredientListRepository makeRepository(List<Ingredient> ingredientList) {
        for (Ingredient ingredient : ingredientList) {
            addIngredient(ingredient);
        }
        return this;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return myIngredients;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        myIngredients.add(ingredient);
    }

    @Override
    public void deleteIngredient(int id) {
        for (Ingredient ingredient : myIngredients) {
            if (ingredient.getId() == (id)) {
                myIngredients.remove(ingredient);
                break;
            }
        }
        System.out.println("Ingredient ID doesn't exist");
    }

    @Override
    public int getNextId() {
        return myIngredients.size() + 1; // Might have to change this
    }

    @Override
    public int numberOfIngredients() {
        return myIngredients.size();
    }

    @Override
    public void updateQuantity(int id, double quantity) {
        for (Ingredient ingredient : myIngredients) {
            if (ingredient.getId() == (id)) {
                ingredient.setQuantity(quantity);
                break;
            }
        }
        System.out.println("Ingredient ID doesn't exist");
    }
}
