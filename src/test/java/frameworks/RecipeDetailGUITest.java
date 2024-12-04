package frameworks;

import frameworks.gui.RecipeDetailGUI;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import interface_adapters.presentation.RecipeViewModel;
import javax.swing.*;

class RecipeDetailGUITest {
    @Mock private RecipeDetailGUI.NavigationListener navigationListener;

    @Test
    void testDisplayRecipeDetail() {
        SwingUtilities.invokeLater(() -> {
            // Create GUI
            RecipeDetailGUI gui = new RecipeDetailGUI(navigationListener);

            RecipeViewModel recipe = new RecipeViewModel(
                    "Test Recipe",
                    "imageUrl",
                    "500 calories",
                    "30 mins",
                    "recipeUrl"
            );

            // Execute
            gui.displayRecipeDetail(recipe);

            gui.dispose();
        });
    }
}
