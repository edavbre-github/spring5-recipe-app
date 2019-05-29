package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UomServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UomServiceImpl uomService;
    private UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        uomToUomCommand = new UnitOfMeasureToUnitOfMeasureCommand();
        uomService = new UomServiceImpl(unitOfMeasureRepository, uomToUomCommand);
    }

    @Test
    public void listAllUoms() {
        // Given
        Set<UnitOfMeasure> uomData = new HashSet<>();

        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        uomData.add(unitOfMeasure1);

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        uomData.add(unitOfMeasure2);

        when(unitOfMeasureRepository.findAll()).thenReturn(uomData);

        // When
        Set<UnitOfMeasureCommand> uoms = uomService.listAllUoms();

        // Then
        assertEquals(2, uoms.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
        verify(unitOfMeasureRepository, never()).findById(anyLong());
    }
}