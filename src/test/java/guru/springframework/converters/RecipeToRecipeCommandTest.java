package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        // Given
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(NOTES);
        recipe.setNotes(notes);

        Category cat1 = new Category();
        cat1.setId(CAT_ID1);
        Category cat2 = new Category();
        cat2.setId(CAT_ID2);

        recipe.getCategories().add(cat1);
        recipe.getCategories().add(cat2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGRED_ID1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        // When
        RecipeCommand command = converter.convert(recipe);

        // Then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());
    }
}