package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by David Brennan, 29/05/2019, 15:41
 *
 * @author EDAVBRE
 */
public interface UomService {
    /**
     * Retrieve a Set of all Units Of Measure
     *
     * @return A Set<UnitOfMeasure>
     */
    Set<UnitOfMeasureCommand> listAllUoms();
}
