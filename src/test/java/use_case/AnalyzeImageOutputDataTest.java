package use_case;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import entities.Ingredient;
import use_case.analyze_image.AnalyzeImageOutputData;
import java.util.ArrayList;
import java.util.List;

public class AnalyzeImageOutputDataTest {
    @Test
    public void testConstructorWithNonEmptyList() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient("Tomato", 2));
        ingredientList.add(new Ingredient("Onion", 1));

        AnalyzeImageOutputData outputData = new AnalyzeImageOutputData(ingredientList);

        assertNotNull(outputData.getIngredientList()); // Output is NOT empty
        assertEquals(2, outputData.getIngredientList().size()); // Length check
        assertEquals("Tomato", outputData.getIngredientList().get(0).getName()); // Check if order is correct
    }

    @Test
    public void testWithEmptyList() {
        AnalyzeImageOutputData outputData = new AnalyzeImageOutputData(new ArrayList<>());
        assertTrue(outputData.isEmpty());
    }

    @Test
    public void testWithNonEmptyList() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient("Tomato", 2));

        AnalyzeImageOutputData outputData = new AnalyzeImageOutputData(ingredientList);

        assertFalse(outputData.isEmpty());
    }
}
