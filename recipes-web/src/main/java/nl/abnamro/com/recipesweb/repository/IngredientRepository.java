package nl.abnamro.com.recipesweb.repository;

import nl.abnamro.com.recipesweb.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
