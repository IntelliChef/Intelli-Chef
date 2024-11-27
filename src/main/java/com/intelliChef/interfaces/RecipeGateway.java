package com.intelliChef.interfaces;

import com.intelliChef.entities.Recipe;
import java.util.List;

public interface RecipeGateway {
    List<Recipe> loadRecipes();
}

