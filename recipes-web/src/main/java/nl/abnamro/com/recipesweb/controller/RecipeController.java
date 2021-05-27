package nl.abnamro.com.recipesweb.controller;

import nl.abnamro.com.recipesweb.exception.RecipeNotFoundException;
import nl.abnamro.com.recipesweb.model.Recipe;
import nl.abnamro.com.recipesweb.model.dto.RecipeDto;
import nl.abnamro.com.recipesweb.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RecipeController is REST API and have below endpoints
 * <p>Create</p> to create Recipe
 * <p>Update</p> to edit or update recipe
 * <p>Delete</p> to delete recipe
 * <p>Get</p> to get all available recipes
 *
 */
@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * getRecipeById endpoint is get the recipe by id.
     * if recipe not found in the data base it will throw RecipeNotFoundException
     *
     * @param id recipeId
     * @return RecipeDto
     * @throws RecipeNotFoundException
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable("id") final int id) throws RecipeNotFoundException {
        Recipe recipe = recipeService.getRecipeById(id);
        return new ResponseEntity<>(RecipeDto.from(recipe), HttpStatus.OK);
    }

    /**
     * getAllRecipe end point will get all recipes from database.
     * if list is empty it will send Http status is 404 and message.
     *
     * @return list<RecipeDto>
     */
    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipe() {
        List<Recipe> recipeList = recipeService.getAllRecipes();
        if(recipeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<RecipeDto> recipeDtoList = recipeList.stream().map(RecipeDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(recipeDtoList, HttpStatus.OK);
    }

    /**
     * saveRecipe endpoint is used to save the recipe object in database.
     *
     * @param recipeDto recipeDto
     * @return String message for recipe saved successfully.
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> saveRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.saveRecipe(Recipe.from(recipeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("Recipe saved successfully..");
    }

    /**
     * update endpoint is used to update/edit the existing recipe object else throw RecipeNotFoundException .
     *
     * @param recipeDto recipeDto from frontend
     * @param id recipeId
     * @return recipeDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> update(@RequestBody RecipeDto recipeDto, @PathVariable("id") int id) {
        Recipe recipe = recipeService.updateRecipe(id, Recipe.from(recipeDto));
        return new ResponseEntity<>(RecipeDto.from(recipe), HttpStatus.OK);
    }

    /**
     * deleteRecipeById endpoint is used to delete existing recipe object.
     * @param id recipe Id
     * @return String with successMessage
     * @throws RecipeNotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipeById(@PathVariable("id") int id) throws RecipeNotFoundException {
        recipeService.deleteRecipe(id);
        return ResponseEntity.status(HttpStatus.OK).body("Recipe deleted successfully..");
    }
}
