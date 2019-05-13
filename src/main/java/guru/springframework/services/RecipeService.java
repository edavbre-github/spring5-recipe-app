package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * Created by David Brennan, 27/03/2019, 19:04
 *
 * @author edavbre
 */
public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
