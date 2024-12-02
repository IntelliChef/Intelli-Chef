package com.intelliChef.use_case;

import use_case.view_interactors.SelectRecipeInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import use_case.dto.RecipeDTO;
import use_case.ports.output.SelectRecipeOutputPort;


@ExtendWith(MockitoExtension.class)
class SelectRecipeInteractorTest {
    @Mock private SelectRecipeOutputPort outputPort;
    private SelectRecipeInteractor interactor;

    @BeforeEach
    void setUp() {
        interactor = new SelectRecipeInteractor(outputPort);
    }

    @Test
    void testSuccessfulRecipeSelection() {
        // Arrange
        RecipeDTO recipe = new RecipeDTO("Test Recipe", "imageUrl", 500.0, 30, "recipeUrl");

        // Act
        interactor.selectRecipe(recipe);

        // Assert
        verify(outputPort).presentRecipeDetails(recipe);
        verifyNoMoreInteractions(outputPort);
    }

    @Test
    void testNullRecipeSelection() {
        // Act
        interactor.selectRecipe(null);

        // Assert
        verify(outputPort).presentRecipeDetails(null);
        verifyNoMoreInteractions(outputPort);
    }
}