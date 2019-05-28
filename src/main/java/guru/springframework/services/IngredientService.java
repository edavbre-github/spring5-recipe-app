package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/**
 * Created by David Brennan, 23/05/2019, 19:34
 *
 * @author edavbre
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
