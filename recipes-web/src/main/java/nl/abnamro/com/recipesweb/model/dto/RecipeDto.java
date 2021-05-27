package nl.abnamro.com.recipesweb.model.dto;

import lombok.Data;
import nl.abnamro.com.recipesweb.model.Recipe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RecipeDto {

    private int id;

    private String name;

    private int servings;

    private String created;

    private Boolean vegetarian;

    private String instructions;

    private List<IngredientDto> ingredients = new ArrayList<>();

    public static RecipeDto from(Recipe recipe){
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(recipe.getId());
        recipeDto.setName(recipe.getName());
        recipeDto.setServings(recipe.getServings());
        recipeDto.setCreated(formatDateAndTime(recipe.getCreated()));
        recipeDto.setVegetarian(recipe.getVegetarian());
        recipeDto.setInstructions(recipe.getInstructions());
        recipeDto.setIngredients(recipe.getIngredients().stream().map(IngredientDto::from).collect(Collectors.toList()));
        return recipeDto;
    }

    private static String formatDateAndTime(LocalDateTime created) {
        return created.format(DateTimeFormatter.ofPattern("dd‐MM‐yyyy HH:mm"));
    }
}
