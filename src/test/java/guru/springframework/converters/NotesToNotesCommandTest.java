package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    public static final Long ID = new Long(1L);
    public static final String NOTES = "Recipe Notes";
    NotesToNotesCommand converter;
    // TODO - Check that this is correct
    // RecipeCommand recipeCommand;

    @Before
    public void setUp() {
        converter = new NotesToNotesCommand();
        // recipeCommand = new RecipeCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        // Given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(NOTES);
        //notes.setRecipe();

        // When
        NotesCommand command = converter.convert(notes);

        // Then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(NOTES, command.getRecipeNotes());
        //assertEquals(null, command.getRecipe());
    }
}