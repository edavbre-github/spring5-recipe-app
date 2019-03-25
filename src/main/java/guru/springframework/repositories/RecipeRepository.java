package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by David Brennan, 21/03/2019, 18:07
 *
 * @author edavbre
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
