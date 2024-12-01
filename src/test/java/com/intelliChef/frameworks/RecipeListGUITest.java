package com.intelliChef.frameworks;

import com.intelliChef.frameworks.gui.RecipeListGUI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.intelliChef.adapters.presentation.RecipeViewModel;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

@ExtendWith(MockitoExtension.class)
class RecipeListGUITest {
    @Mock private RecipeListGUI.RecipeSelectionListener selectionListener;

    @Test
    void testDisplayRecipes() {
        // This test needs to run on EDT
        SwingUtilities.invokeLater(() -> {
            // Create GUI
            RecipeListGUI gui = new RecipeListGUI(selectionListener);

            // Prepare test data
            List<RecipeViewModel> recipes = Arrays.asList(
                    new RecipeViewModel("Recipe1", "url1", "500 calories", "30 mins", "recipeUrl1"),
                    new RecipeViewModel("Recipe2", "url2", "600 calories", "45 mins", "recipeUrl2")
            );

            // Execute
            gui.displayRecipes(recipes);

            // Visual verification would require manual testing
            // Here we just verify no exceptions are thrown
            gui.dispose();
        });
    }
}
