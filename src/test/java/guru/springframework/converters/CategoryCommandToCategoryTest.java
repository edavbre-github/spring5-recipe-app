package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final Long ID = new Long(1L);
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        // Given
        CategoryCommand command = new CategoryCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        // When
        Category category = converter.convert(command);

        // Then
        assertNotNull(category);
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}