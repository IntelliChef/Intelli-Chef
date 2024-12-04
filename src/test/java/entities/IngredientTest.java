package entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    @Test
    public void testConstructor() {
        Ingredient ingredient = new Ingredient("Sugar", 2.5);
        assertEquals("Sugar", ingredient.getName());
        assertEquals(2.5, ingredient.getQuantity());
        assertTrue(ingredient.isSelected());
    }

    @Test
    public void testSetName() {
        Ingredient ingredient = new Ingredient("Sugar", 2.5);
        ingredient.setName("Salt");
        assertEquals("Salt", ingredient.getName());
    }

    @Test
    public void testSetQuantity() {
        Ingredient ingredient = new Ingredient("Sugar", 2.5);
        ingredient.setQuantity(3.0);
        assertEquals(3.0, ingredient.getQuantity());
    }

    @Test
    public void testSetSelected() {
        Ingredient ingredient = new Ingredient("Sugar", 2.5);
        ingredient.setSelected(false);
        assertFalse(ingredient.isSelected());
    }

    @Test
    public void testSetId() {
        Ingredient ingredient = new Ingredient("Sugar", 2.5);
        ingredient.setId(101);
        assertEquals(101, ingredient.getId());
    }

    @Test
    public void testGetId() {
        Ingredient ingredient = new Ingredient("Sugar", 2.5);
        ingredient.setId(101);
        assertEquals(101, ingredient.getId());
    }
}

