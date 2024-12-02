package com.intelliChef.use_case;

import use_case.view_interactors.LoadRecipesInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import entities.Recipe;
import use_case.ports.output.LoadRecipesOutputPort;
import use_case.ports.output.RecipeRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LoadRecipesInteractorTest {
    @Mock private RecipeRepository repository;
    @Mock private LoadRecipesOutputPort outputPort;
    private LoadRecipesInteractor interactor;

    @BeforeEach
    void setUp() {
        interactor = new LoadRecipesInteractor(repository, outputPort);
    }

    @Test
    void testSuccessfulRecipeLoad() {
        // Arrange
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Recipe1", "url1", 500.0, 30, "recipeUrl1"),
                new Recipe("Recipe2", "url2", 600.0, 45, "recipeUrl2")
        );
        when(repository.fetchRecipes()).thenReturn(recipes);

        // Act
        interactor.loadRecipes();

        // Assert
        verify(outputPort).presentRecipes(anyList());
        verifyNoMoreInteractions(outputPort);
    }

    @Test
    void testEmptyRecipeList() {
        // Arrange
        when(repository.fetchRecipes()).thenReturn(Collections.emptyList());

        // Act
        interactor.loadRecipes();

        // Assert
        verify(outputPort).presentRecipes(Collections.emptyList());
        verifyNoMoreInteractions(outputPort);
    }

    @Test
    void testRepositoryException() {
        // Arrange
        when(repository.fetchRecipes()).thenThrow(new RuntimeException("Test error"));

        // Act
        interactor.loadRecipes();

        // Assert
        verify(outputPort).presentError("Failed to load recipes: Test error");
        verifyNoMoreInteractions(outputPort);
    }

    @Test
    void testNullRecipeInList() {
        // Arrange
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Recipe1", "url1", 500.0, 30, "recipeUrl1"),
                null
        );
        when(repository.fetchRecipes()).thenReturn(recipes);

        // Act
        interactor.loadRecipes();

        // Assert
        verify(outputPort).presentError(anyString());
        verifyNoMoreInteractions(outputPort);
    }
}