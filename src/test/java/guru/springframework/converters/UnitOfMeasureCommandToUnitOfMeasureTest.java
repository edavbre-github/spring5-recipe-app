package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final Long ID = new Long(1L);
    public static final String DESCRIPTION = "description";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {
        // Given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        // When
        UnitOfMeasure uom = converter.convert(command);

        // Then
        assertNotNull(uom);
        assertEquals(ID, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}