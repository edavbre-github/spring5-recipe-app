package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/**
 * Created by David Brennan, 23/05/2019, 19:34
 *
 * @author edavbre
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    /**
     * @deprecated Use deleteById(Long, Long) instead
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * Delete an ingredient from a recipe
     *
     * @param recipeId Id of the recipe
     * @param idToDelete Id of the ingredient to be deleted
     */
    void deleteById(Long recipeId, Long idToDelete);
}
