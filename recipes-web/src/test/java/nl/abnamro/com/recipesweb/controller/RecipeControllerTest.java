package nl.abnamro.com.recipesweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.abnamro.com.recipesweb.model.Recipe;
import nl.abnamro.com.recipesweb.model.dto.RecipeDto;
import nl.abnamro.com.recipesweb.service.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RecipeController.class)
public class RecipeControllerTest {

    @MockBean
    private RecipeService recipeService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @WithMockUser(username = "admin")
    @Test
    public void givenRecipeDto_whenSaveRecipe_thenStatus200WithSuccessMessage() throws Exception {
       //Given recipeDto from postman or frontend
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName("Chicken");
        recipeDto.setVegetarian(true);
        recipeDto.setServings(4);

        //When saveRecipe
        Recipe recipe = new Recipe();
        recipe.setName("Chicken Biriyani");
        recipe.setVegetarian(true);
        recipe.setServings(4);

       when(recipeService.saveRecipe(any(Recipe.class))).thenReturn(recipe);

       //Then Controller should return Status is ok and Success message
        mockMvc.perform(post("/recipe")
                .content(objectMapper.writeValueAsString(recipeDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Recipe saved successfully..")));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void shouldReturn200WhenSendingRequestToControllerWithRoleUser() throws Exception {
        mockMvc.perform(get("/recipe")).andExpect(status().isNoContent());
    }

    @WithMockUser(username = "admin")
    @Test
    public void givenRecipeId_when_GetRecipeById_thenRecipeDto_WithStatus200WithSuccessMessage() throws Exception {
        //Given recipeDto from postman or frontend
        // Given recipeId as input from postman
        int recipeId = 2;

        //When called recipeService.saveOrUpdate
        Recipe recipe = new Recipe();
        recipe.setId(2);
        recipe.setName("Kabab");
        recipe.setVegetarian(true);
        recipe.setServings(5);
        when(recipeService.getRecipeById(any(Integer.class))).thenReturn(recipe);


        //Then Controller should return Status is ok and Success message
        mockMvc.perform(get("/recipe/2")
                .content(objectMapper.writeValueAsString(recipe))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(recipe.getName()))
                .andExpect(jsonPath("$.vegetarian").value(recipe.getVegetarian()))
                .andExpect(jsonPath("$.servings").value(recipe.getServings()));

    }

    @WithMockUser(username = "admin")
    @Test
    public void when_GetAllRecipe_thenReturnListRecipeDto_WithStatus200WithSuccessMessage() throws Exception {
        //Given recipeDto from postman or frontend
        // Given recipeId as input from postman
        int recipeId = 2;

        when(recipeService.getAllRecipes()).thenReturn(getRecipeList());


        //Then Controller should return Status is ok and Success message
        mockMvc.perform(get("/recipe")
                .content(objectMapper.writeValueAsString(getRecipeList()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"id\":1,\"name\":\"Donor Kebab\",\"servings\":5,\"created\":\"24‐05‐2021 10:15\",\"vegetarian\":false,\"instructions\":null,\"ingredients\":[]},{\"id\":2,\"name\":\"Chicken Kebab\",\"servings\":2,\"created\":\"24‐05‐2021 10:15\",\"vegetarian\":false,\"instructions\":null,\"ingredients\":[]},{\"id\":3,\"name\":\"French Fries\",\"servings\":3,\"created\":\"24‐05‐2021 10:15\",\"vegetarian\":true,\"instructions\":null,\"ingredients\":[]}]", true));

    }

    //Test scenario data.
    private List<Recipe> getRecipeList() {
        List<Recipe> recipeList = new ArrayList<>();
        Clock clock = Clock.fixed(Instant.parse("2021-05-24T10:15:30.00Z"), ZoneId.of("UTC"));
        LocalDateTime dateTime = LocalDateTime.now(clock);
        Recipe recipe1 = new Recipe();
        recipe1.setId(1);
        recipe1.setName("Donor Kebab");
        recipe1.setCreated(dateTime);
        recipe1.setVegetarian(false);
        recipe1.setServings(5);

        Recipe recipe2 = new Recipe();
        recipe2.setId(2);
        recipe2.setName("Chicken Kebab");
        recipe2.setCreated(dateTime);
        recipe2.setVegetarian(false);
        recipe2.setServings(2);

        Recipe recipe3 = new Recipe();
        recipe3.setId(3);
        recipe3.setName("French Fries");
        recipe3.setCreated(dateTime);
        recipe3.setVegetarian(true);
        recipe3.setServings(3);

        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);
        return recipeList;
    }

    @WithMockUser(username = "admin")
    @Test
    public void givenRecipeId_RecipeDto_when_update_thenRecipeDto_WithStatus200WithSuccessMessage() throws Exception {
        //Given recipeDto from postman or frontend
        // Given recipeId as input from postman
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
        recipe.setServings(4);
        when(recipeService.updateRecipe((any(Integer.class)), any(Recipe.class))).thenReturn(recipe);


        //Then Controller should return Status is ok and Success message
        mockMvc.perform(put("/recipe/1")
                .content(objectMapper.writeValueAsString(recipeDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(recipe.getName()))
                .andExpect(jsonPath("$.vegetarian").value(recipe.getVegetarian()))
                .andExpect(jsonPath("$.servings").value(recipe.getServings()));

    }

    @WithMockUser(username = "admin")
    @Test
    public void givenRecipeId_whenDeleteRecipeById_thenStatus200WithSuccessMessage() throws Exception {
        //Given recipeDto from postman or frontend
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(1);
        recipeDto.setName("Chicken");
        recipeDto.setVegetarian(true);
        recipeDto.setServings(4);



        //Then Controller should return Status is ok and Success message
        mockMvc.perform(delete("/recipe/1")
                //.content(objectMapper.writeValueAsString(recipeDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Recipe deleted successfully..")));

    }

}
