package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by David Brennan, 23/05/2019, 19:42
 *
 * @author edavbre
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        // TODO - implement error handling
        if (!optionalRecipe.isPresent()) {
            log.error("Recipe with id={} not found", recipeId);
        }

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        // TODO - implement error handling
        if (!ingredientCommandOptional.isPresent()) {
            log.error("Ingredient with id={} not found", ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        // This was my miserable attempt!!
//        Ingredient detachedIngredient = ingredientCommandToIngredient.convert(command);
//        Ingredient savedIngredient = ingredientRepository.save(detachedIngredient);
//        log.debug("Saved Ingredient ID: {}", savedIngredient.getId());
//        return ingredientToIngredientCommand.convert(savedIngredient);

        // This is how it was supposed to be done...
        Optional<Recipe> optionalRecipe = recipeRepository.findById(command.getRecipeId());
        if (!optionalRecipe.isPresent()) {
            // TODO - throw a suitable error/exception if not found
            log.error("Recipe not found for ID: {}", command.getRecipeId());
            return new IngredientCommand();
        }

        Recipe recipe = optionalRecipe.get();
        Optional<Ingredient> optionalIngredient = getIngredientFromRecipe(command.getId(), recipe);

        if (optionalIngredient.isPresent()) {
            // Update existing ingredient
            Ingredient foundIngredient = optionalIngredient.get();
            foundIngredient.setDescription(command.getDescription());
            foundIngredient.setAmount(command.getAmount());
            foundIngredient.setUom(unitOfMeasureRepository
                    .findById(command.getUom().getId())
                    .orElseThrow(() -> new RuntimeException("UOM Not Found!!"))); // TODO - address this prpoerly
        } else {
            // New ingredient
            Ingredient ingredient = ingredientCommandToIngredient.convert(command);
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        Optional<Ingredient> savedOptionalIngredient = getIngredientFromRecipe(command.getId(), savedRecipe);

        if (!savedOptionalIngredient.isPresent()) {
            // This is not totally safe, but a best guess. Will do for now
            savedOptionalIngredient = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                    .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                    .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                    .findFirst();
        }

        return ingredientToIngredientCommand.convert(savedOptionalIngredient.get());
    }

    /*
     * This was my stab at deleting a recipe ingredient. It did work, ingredients were being
     * successfully deleted from a recipe and tests all passed, but Mr T's code is rather more
     * complicated and can be seen below.
     *
     * Actually, I don't think Mr T's code is correct. All he's doing is nulling the recipe id in
     * the ingredient entry, but the ingredient is not actually being deleted. This is resulting
     * in "leaked" ingredient entries.
     *
     * @param idToDelete
     */
     @Override
     public void deleteById(Long idToDelete) {
        ingredientRepository.deleteById(idToDelete);
     }

    @Override
    public void deleteById(Long recipeId, Long idToDelete) {
         Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
         if (!optRecipe.isPresent()) {
             log.debug("Recipe id {} not found, cannot delete ingredient {}", recipeId, idToDelete);
             return;
         }

         Recipe recipe = optRecipe.get();

        Optional<Ingredient> optIngredient = getIngredientFromRecipe(idToDelete, recipe);

        if (!optIngredient.isPresent()) {
            log.debug("Ingredient id {} not found, cannot delete it", idToDelete);
            return;
        }

        Ingredient ingredientToDelete = optIngredient.get();
        // See notes in deleteById(Long)
        ingredientToDelete.setRecipe(null);
        recipe.getIngredients().remove(ingredientToDelete);
        recipeRepository.save(recipe);
    }

    private Optional<Ingredient> getIngredientFromRecipe(Long ingredientId, Recipe recipe) {
        return recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();
    }
}
