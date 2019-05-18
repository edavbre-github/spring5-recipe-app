package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long ID = new Long(1L);
    public static final String DESCRIPTION = "description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        // Given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);

        // When
        UnitOfMeasureCommand uomc = converter.convert(uom);

        // Then
        assertNotNull(uomc);
        assertEquals(ID, uomc.getId());
        assertEquals(DESCRIPTION, uomc.getDescription());
    }
}