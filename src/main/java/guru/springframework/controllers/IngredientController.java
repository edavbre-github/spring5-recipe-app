package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by David Brennan, 22/05/2019, 18:38
 *
 * @author edavbre
 */
@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private UomService uomService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UomService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredients list for recipe id: {}", recipeId);

        // Use a command object to avoid lazy load errors in ThymeLeaf
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        log.debug("Show ingredient details for ingredient id: {}", id);

        // Use a command object to avoid lazy load errors in ThymeLeaf
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        return "recipe/ingredient/show";
    }

    /**
     * Mr T calls this method newRecipe which I don't like (was possibly a cut and paste error).
     * Anyway, as it's supposed to be for creating a new ingredient, I'm calling it newRecipeIngredient.
     *
     * @param recipeId
     * @param model
     * @return
     */
    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newRecipeIngredient(@PathVariable String recipeId, Model model) {
        log.debug("Create a new ingredient for recipe ID: {}", recipeId);
        // TODO - should throw exception if no recipe is found
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        // Need to return back the parent recipe id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);
        // Init the uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        log.debug("Edit ingredient details for ingredient id: {}", id);

        // Use a command object to avoid lazy load errors in ThymeLeaf
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteRecipeIngredient(@PathVariable String recipeId, @PathVariable String id) {
        log.debug("Deleting ingredient: {} for recipe: {}", id, recipeId);

        // Delete the requested ingredient by id
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

        // Display the remaining recipe ingredients
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("Saved ingredient ID: {}", savedCommand.getId());
        log.debug("Saved recipe ID: {}", savedCommand.getRecipeId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}
