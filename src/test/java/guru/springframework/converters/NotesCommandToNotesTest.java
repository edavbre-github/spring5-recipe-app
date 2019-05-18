package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final Long ID = new Long(1L);
    public static final String NOTES = "Recipe Notes";
    NotesCommandToNotes converter;
    // TODO - Check that this is correct
    // RecipeCommand recipeCommand;

    @Before
    public void setUp() {
        converter = new NotesCommandToNotes();
        // recipeCommand = new RecipeCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        // Given
        NotesCommand command = new NotesCommand();
        command.setId(ID);
        command.setRecipeNotes(NOTES);
        //notes.setRecipe();

        // When
        Notes notes = converter.convert(command);

        // Then
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
        //assertEquals(null, command.getRecipe());
    }
}