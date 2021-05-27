package nl.abnamro.com.recipesweb.service;

import nl.abnamro.com.recipesweb.exception.RecipeNotFoundException;
import nl.abnamro.com.recipesweb.model.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe saveRecipe(Recipe recipe);

    void deleteRecipe(int id) throws RecipeNotFoundException;

    Recipe getRecipeById(int id) throws RecipeNotFoundException;

    List<Recipe> getAllRecipes();

    Recipe updateRecipe(int id, Recipe recipe);

}
