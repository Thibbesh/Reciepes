package nl.abnamro.com.recipesweb.model;

import lombok.Data;
import nl.abnamro.com.recipesweb.model.dto.IngredientDto;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientId;

    @Column(name="name")
    private String name;

    @Column(name="amount")
    private String amount;

    @ManyToOne()
    private Recipe recipe;

    public static Ingredient from(IngredientDto ingredientDto){
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(ingredientDto.getIngredientId());
        ingredient.setName(ingredientDto.getName());
        ingredient.setAmount(ingredientDto.getAmount());
        return ingredient;
    }
}
