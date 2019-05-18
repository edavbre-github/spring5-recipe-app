package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    public static final Long ID = new Long(1L);
    public static final Long UOM_ID = new Long(2L);
    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final String DESCRIPTION = "Cheeseburger";

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand((new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertNullUom() {
        // Given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setRecipe(RECIPE);
        ingredient.setUom(null);

        // When
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        // Then
        assertNull(ingredientCommand.getUom());
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }

    @Test
    public void testConvertWithUom() {
        // Given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setRecipe(RECIPE);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        // When
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        // Then
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertNotNull(ingredientCommand.getUom());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());
    }
}