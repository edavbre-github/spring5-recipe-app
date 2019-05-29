package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by David Brennan, 29/05/2019, 15:44
 *
 * @author EDAVBRE
 */
@Slf4j
@Service
public class UomServiceImpl implements UomService {

    private final UnitOfMeasureRepository uomRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand;

    public UomServiceImpl(UnitOfMeasureRepository uomRepository, UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand) {
        this.uomRepository = uomRepository;
        this.uomToUomCommand = uomToUomCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        log.debug("In the listAllUoms method.");
        return StreamSupport.stream(uomRepository.findAll()
                .spliterator(), false)
                .map(uomToUomCommand::convert)
                .collect(Collectors.toSet());
    }
}