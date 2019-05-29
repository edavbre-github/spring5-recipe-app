package guru.springframework.repositories;

import guru.springframework.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by David Brennan, 29/05/2019, 16:37
 *
 * May not be needed just yet!!
 *
 * @author EDAVBRE
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
