package com.intelliChef.use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.intelliChef.entities.Recipe;
import com.intelliChef.use_case.ports.output.LoadRecipesOutputPort;
import com.intelliChef.use_case.ports.output.RecipeRepository;

import java.util.Arrays;
import java.util.List;

class LoadRecipesInteractorTest {
    @Mock private RecipeRepository repository;
    @Mock private LoadRecipesOutputPort outputPort;
    private LoadRecipesInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        interactor = new LoadRecipesInteractor(repository, outputPort);
    }

    @Test
    void testSuccessfulRecipeLoad() {
        // Prepare test data
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Recipe1", "url1", 500.0, 30, "recipeUrl1"),
                new Recipe("Recipe2", "url2", 600.0, 45, "recipeUrl2")
        );

        // Set up mock behavior
        when(repository.fetchRecipes()).thenReturn(recipes);

        // Execute
        interactor.loadRecipes();

        verify(outputPort).presentRecipes(anyList());
        verifyNoMoreInteractions(outputPort);
    }

    @Test
    void testFailedRecipeLoad() {
        // Set up mock behavior
        when(repository.fetchRecipes()).thenThrow(new RuntimeException("Test error"));

        // Execute
        interactor.loadRecipes();

        verify(outputPort).presentError(anyString());
        verifyNoMoreInteractions(outputPort);
    }
}