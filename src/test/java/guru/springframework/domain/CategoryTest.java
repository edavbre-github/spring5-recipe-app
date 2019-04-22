package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by David Brennan, 22/04/2019, 15:46
 *
 * @author edavbre
 */
public class CategoryTest {

    private Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() throws Exception {
        String desc = "Test description";
        category.setDescription(desc);
        assertEquals(desc, category.getDescription());
    }

    @Test
    public void getRecipes() throws Exception {
        // TODO
    }
}
