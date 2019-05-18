package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final Long ID = new Long(1L);
    public static final String DESCRIPTION = "description";
    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        // Given
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        // When
        CategoryCommand catCmd = converter.convert(category);

        // Then
        assertNotNull(catCmd);
        assertEquals(ID, catCmd.getId());
        assertEquals(DESCRIPTION, catCmd.getDescription());
    }
}