package use_case;

import entities.Ingredient;

import java.util.List;

public interface IngredientRepository {
    /**
     * Returns all ingredients that are checked.
     * @return list of ingredients.
     */
    List<Ingredient> getAllIngredients();
    void addIngredient(Ingredient ingredient);

    /**
     * Delete ingredient with the id
     * @param id the id of the ingredient to be deleted.
     */
    void deleteIngredient(int id);

    /**
     * Return the next possible id
     * @return the next id
     */
    int getNextId();

    /**
     * To count number of ingredients.
     * @return the number of ingredients in repository
     */
    int numberOfIngredients();

    /**
     * Update the quantity of the ingredient with the given id
     * @param id id of the ingredient to be updated
     * @param quantity updated quantity
     */
    void updateQuantity(int id, double quantity);
}
