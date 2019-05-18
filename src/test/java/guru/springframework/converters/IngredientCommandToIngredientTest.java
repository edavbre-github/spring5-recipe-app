package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final Long ID = new Long(1L);
    public static final Long UOM_ID = new Long(2L);
    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final String DESCRIPTION = "Cheeseburger";

    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void testConvertNullUom() {
        // Given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
//        command.setRecipe(RECIPE);
        command.setUom(null);

        // When
        Ingredient ingredient = converter.convert(command);

        // Then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }

    @Test
    public void testConvertWithUom() {
        // Given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        // command.setRecipe(RECIPE);
        UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
        uomc.setId(UOM_ID);
        command.setUom(uomc);

        // When
        Ingredient ingredient = converter.convert(command);

        // Then
        assertEquals(ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertNotNull(ingredient.getUom());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }
}