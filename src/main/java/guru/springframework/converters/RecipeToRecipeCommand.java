package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by David Brennan, 16/05/2019, 18:41
 *
 * @author EDAVBRE
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setPrepTime(source.getPrepTime());
        command.setCookTime(source.getCookTime());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setDirections(source.getDirections());
        command.setDifficulty(source.getDifficulty());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            // TODO - Should the following be .forEach(category... or .forEach((Category category)...
            // TODO - Or does it matter?
            source.getCategories().forEach(category -> command.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}
