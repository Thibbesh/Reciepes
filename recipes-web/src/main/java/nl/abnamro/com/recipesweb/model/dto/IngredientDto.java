package nl.abnamro.com.recipesweb.model.dto;

import lombok.Data;
import nl.abnamro.com.recipesweb.model.Ingredient;

@Data
public class IngredientDto {

    private int ingredientId;

    private String name;

    private String amount;

    public static IngredientDto from(Ingredient ingredient){
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setIngredientId(ingredient.getIngredientId());
        ingredientDto.setName(ingredient.getName());
        ingredientDto.setAmount(ingredient.getAmount());
        return ingredientDto;
    }
}
