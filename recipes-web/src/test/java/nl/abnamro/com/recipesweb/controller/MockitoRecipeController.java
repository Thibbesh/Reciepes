package nl.abnamro.com.recipesweb.controller;

import nl.abnamro.com.recipesweb.model.Ingredient;
import nl.abnamro.com.recipesweb.model.Recipe;
import nl.abnamro.com.recipesweb.model.dto.IngredientDto;
import nl.abnamro.com.recipesweb.model.dto.RecipeDto;
import nl.abnamro.com.recipesweb.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MockitoRecipeController {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    //Test scenario# : 1
    @Test
    public void givenRecipeDto_whenSaveRecipe_thenSatus200WithSuccessMessage()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        // Given input recipeDTO as input from postman
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("Kabab");
        recipeDto.setVegetarian(true);
        recipeDto.setServings(5);
        recipeDto.setInstructions("1. Turn on the gas," +
                                   "2. Chop chicken n onion" +
                                   "3. spoons of oil");

        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        IngredientDto ingredientDto1 = new IngredientDto();
        ingredientDto1.setAmount("2");
        ingredientDto1.setName("Chicken");
        IngredientDto ingredientDto2 = new IngredientDto();
        ingredientDto2.setAmount("2 spoons");
        ingredientDto2.setName("Oil");
        ingredientDtoList.add(ingredientDto1);
        ingredientDtoList.add(ingredientDto2);
        recipeDto.setIngredients(ingredientDtoList);


        //When called recipeService.saveOrUpdate
        Recipe recipe = new Recipe();
        recipe.setName("Kabab");
        recipe.setVegetarian(true);
        recipe.setServings(5);
        when(recipeService.saveRecipe(any(Recipe.class))).thenReturn(recipe);

        //Then return StatusCode as 200 and Success message..
        ResponseEntity<String> responseEntity = recipeController.saveRecipe(recipeDto);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo("Recipe saved successfully..");
    }

    /**
     *
     */
    //Test scenario# : 2
    @Test
    public void givenRecipeById_whenGetRecipeById_thenGetRecipeDto_Status200()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Clock clock = Clock.fixed(Instant.parse("2021-05-24T10:15:30.00Z"), ZoneId.of("UTC"));
        LocalDateTime dateTime = LocalDateTime.now(clock);
        // Given recipeId as input from postman
        int recipeId = 2;

        //When called recipeService.saveOrUpdate
        Recipe recipe = new Recipe();
        recipe.setId(2);
        recipe.setName("Kabab");
        recipe.setVegetarian(true);
        recipe.setCreated(dateTime);
        recipe.setServings(5);
        recipe.setInstructions("1. Turn on the gas," +
                "2. Chop chicken n onion" +
                "3. 4 spoons of oil");

        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setAmount("2");
        ingredient1.setName("Chicken");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setAmount("2 spoons");
        ingredient2.setName("Oil");
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        recipe.setIngredients(ingredientList);
        when(recipeService.getRecipeById(any(Integer.class))).thenReturn(recipe);

        //Then return StatusCode as 200 and Success message..
        ResponseEntity<RecipeDto> recipeDto = recipeController.getRecipeById(recipeId);
        assertThat(recipeDto.getStatusCodeValue()).isEqualTo(200);
        assertEquals("RecipeDto Id: ", recipeDto.getBody().getId(), recipe.getId());
        assertEquals("Name of the recipe: ", recipeDto.getBody().getName(), recipe.getName());
        assertTrue("Indicator if the dish is vegetarian: ", recipeDto.getBody().getVegetarian());
        assertEquals("date and time of creation", recipeDto.getBody().getCreated(), "24‐05‐2021 10:15");
        assertEquals("Number of people the dish is suitable for: ", recipeDto.getBody().getServings(), recipe.getServings());
        assertEquals("Cooking instruction: ", recipeDto.getBody().getInstructions(), recipe.getInstructions());
        assertEquals("List of Ingredients ", recipeDto.getBody().getIngredients().size(), recipe.getIngredients().size());
    }

    //Test scenario# : 3
    @Test
    public void loggedUser_Would_likeToGetAllRecipes_thenGetListRecipeDto_WithStatusOk()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        //When called recipeService.saveOrUpdate
        when(recipeService.getAllRecipes()).thenReturn(getRecipeList());

        //Then return StatusCode as 200 and Success message..
        ResponseEntity<List<RecipeDto>> recipeDtoList = recipeController.getAllRecipe();
        assertThat(recipeDtoList.getStatusCodeValue()).isEqualTo(200);
        assertEquals("List of Recipes.", recipeDtoList.getBody().size(), 3);
        assertEquals("Name of the recipe: ", recipeDtoList.getBody().get(0).getName(), "Donor Kebab");
        assertFalse("Indicator if the dish is vegetarian: ", recipeDtoList.getBody().get(0).getVegetarian());
        assertEquals("Number of people the dish is suitable for.: ", recipeDtoList.getBody().get(0).getServings(), 5);
        assertEquals("List of Ingredient: ", recipeDtoList.getBody().get(0).getIngredients().size(), 2);

        assertEquals("Name of the recipe: ", recipeDtoList.getBody().get(1).getName(), "Chicken Kebab");
        assertFalse("Indicator if the dish is vegetarian: ", recipeDtoList.getBody().get(1).getVegetarian());
        assertEquals("Number of people the dish is suitable for:", recipeDtoList.getBody().get(1).getServings(), 2);
        assertEquals("List of Ingredient: ", recipeDtoList.getBody().get(0).getIngredients().size(), 2);

        assertEquals("Name of the recipe: ", recipeDtoList.getBody().get(2).getName(), "French Fries");
        assertTrue("Indicator if the dish is vegetarian: ", recipeDtoList.getBody().get(2).getVegetarian());
        assertEquals("Number of people the dish is suitable for:", recipeDtoList.getBody().get(2).getServings(), 3);
        assertEquals("List of Ingredient: ", recipeDtoList.getBody().get(0).getIngredients().size(), 2);
    }

    //Test scenario data.
    private List<Recipe> getRecipeList() {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1);
        recipe1.setName("Donor Kebab");
        recipe1.setVegetarian(false);
        recipe1.setServings(5);
        recipe1.setInstructions("1. Turn on the gas," +
                "2. Chop chicken n onion" +
                "3. spoons of oil");

        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setAmount("2");
        ingredient1.setName("Chicken");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setAmount("2 spoons");
        ingredient2.setName("Oil");
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        recipe1.setIngredients(ingredientList);

        Recipe recipe2 = new Recipe();
        recipe2.setId(2);
        recipe2.setName("Chicken Kebab");
        recipe2.setVegetarian(false);
        recipe2.setServings(2);
        recipe2.setInstructions("1. Turn on the gas," +
                "2. Chop chicken n onion" +
                "3. spoons of oil");

        List<Ingredient> ingredientList2 = new ArrayList<>();
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setAmount("2");
        ingredient3.setName("Chicken");
        Ingredient ingredient4 = new Ingredient();
        ingredient4.setAmount("2 spoons");
        ingredient4.setName("Oil");
        ingredientList2.add(ingredient3);
        ingredientList2.add(ingredient4);
        recipe2.setIngredients(ingredientList2);

        Recipe recipe3 = new Recipe();
        recipe3.setId(3);
        recipe3.setName("French Fries");
        recipe3.setVegetarian(true);
        recipe3.setServings(3);
        recipe3.setInstructions("1. Turn on the gas," +
                "2. Chop chicken n onion" +
                "3. spoons of oil");

        List<Ingredient> ingredientList3 = new ArrayList<>();
        Ingredient ingredient5 = new Ingredient();
        ingredient5.setAmount("2");
        ingredient5.setName("Chicken");
        Ingredient ingredient6 = new Ingredient();
        ingredient6.setAmount("2 spoons");
        ingredient6.setName("Oil");
        ingredientList2.add(ingredient5);
        ingredientList2.add(ingredient6);
        recipe3.setIngredients(ingredientList3);

        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);
        return recipeList;
    }

    //Test scenario# : 4
    @Test
    public void givenRecipeDtoWithId_WhenUpdateRecipe_ThenStatusOkWithUpdatedRecipeObject()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Clock clock = Clock.fixed(Instant.parse("2021-05-24T10:15:30.00Z"), ZoneId.of("UTC"));
        LocalDateTime dateTime = LocalDateTime.now(clock);
        // Given input recipeDTO as input from postman

        int recipeId = 1;
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(1);
        recipeDto.setName("Donor Kebab");
        recipeDto.setVegetarian(false);
        recipeDto.setServings(5);

        //When called recipeService.saveOrUpdate
        Recipe recipe = new Recipe();
        recipe.setName("French Fries");
        recipe.setVegetarian(true);
        recipe.setCreated(dateTime);
        recipe.setServings(4);
        recipe.setInstructions("1. Turn on the gas," +
                "2. Chop chicken n onion" +
                "3. spoons of oil");

        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setAmount("2");
        ingredient1.setName("Chicken");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setAmount("2 spoons");
        ingredient2.setName("Oil");
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        recipe.setIngredients(ingredientList);
        when(recipeService.updateRecipe((any(Integer.class)), any(Recipe.class))).thenReturn(recipe);

        //Then return StatusCode as 200 and Success message..
        ResponseEntity<RecipeDto> responseEntity = recipeController.update(recipeDto, recipeId);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertEquals("Name of the recipe: ",responseEntity.getBody().getName(), recipe.getName());
        assertTrue("Indicator if the dish is vegetarian: ",responseEntity.getBody().getVegetarian());
        assertEquals("Number of people the dish is suitable for: ",responseEntity.getBody().getServings(), 4);
        assertEquals("Date and time of creation ",responseEntity.getBody().getCreated(), "24‐05‐2021 10:15");
        assertEquals("Cooking instructions ",responseEntity.getBody().getInstructions(), recipe.getInstructions());
        assertEquals("Cooking instructions ",responseEntity.getBody().getIngredients().size(), recipe.getIngredients().size());

    }

    //Test scenario# : 5
    @Test
    public void givenRecipeId_When_deleteRecipeById_thenStatusOkWithSuccessMessage()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        // Given input recipeDTO as input from postman
        int recipeId = 1;
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("Kabab");
        recipeDto.setVegetarian(true);
        recipeDto.setServings(5);

        //When called recipeService.saveOrUpdate
        Recipe recipe = new Recipe();
        recipe.setName("Kabab");
        recipe.setVegetarian(true);
        recipe.setServings(5);
        //when(recipeService.deleteRecipe(any(Integer.class))).then();

        //Then return StatusCode as 200 and Success message..
        ResponseEntity<String> responseEntity = recipeController.deleteRecipeById(recipeId);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo("Recipe deleted successfully..");
    }
}
