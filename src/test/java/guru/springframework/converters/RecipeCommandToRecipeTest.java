package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final Long ID = 1L;
    public static final Integer COOK_TIME = new Integer(5   );
    public static final Integer PREP_TIME = new Integer(10);
    public static final String DESCRIPTION = "Some description";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = new Integer(3);
    public static final String SOURCE = "A source";
    public static final String URL = "http://xxxxxxxx";
    public static final Long CAT_ID1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID1 = 3L;
    public static final Long INGRED_ID2 = 4L;
    public static final Long NOTES_ID = 5L;
    public static final String NOTES = "Some recipe notes";

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        // Given
        RecipeCommand command = new RecipeCommand();
        command.setId(ID);
        command.setCookTime(COOK_TIME);
        command.setPrepTime(PREP_TIME);
        command.setDescription(DESCRIPTION);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        notesCommand.setRecipeNotes(NOTES);
        command.setNotes(notesCommand);

        CategoryCommand cat1 = new CategoryCommand();
        cat1.setId(CAT_ID1);
        CategoryCommand cat2 = new CategoryCommand();
        cat2.setId(CAT_ID2);

        command.getCategories().add(cat1);
        command.getCategories().add(cat2);

        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setId(INGRED_ID1);
        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID2);

        command.getIngredients().add(ingredient1);
        command.getIngredients().add(ingredient2);

        // When
        Recipe recipe = converter.convert(command);

        // Then
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}