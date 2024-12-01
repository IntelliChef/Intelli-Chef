package com.intelliChef.use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.intelliChef.use_case.dto.RecipeDTO;
import com.intelliChef.use_case.ports.output.SelectRecipeOutputPort;

class SelectRecipeInteractorTest {
    @Mock private SelectRecipeOutputPort outputPort;
    private SelectRecipeInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new SelectRecipeInteractor(outputPort);
    }

    @Test
    void testRecipeSelection() {
        RecipeDTO recipeDTO = new RecipeDTO("Test Recipe", "imageUrl", 500.0, 30, "recipeUrl");

        // Execute
        interactor.selectRecipe(recipeDTO);

        verify(outputPort).presentRecipeDetails(recipeDTO);
        verifyNoMoreInteractions(outputPort);
    }
}