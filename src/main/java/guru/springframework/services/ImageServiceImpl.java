package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by David Brennan, 01/06/2019, 13:59
 *
 * @author EDAVBRE
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile image) {
        log.info("Received an image file");
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if (!optRecipe.isPresent()) {
            log.error("Recipe {} not found", recipeId);
            return;
        }

        Recipe recipe = optRecipe.get();

        try {
            Byte[] bytes = new Byte[image.getBytes().length];
            int i = 0;
            for (byte b : image.getBytes()) {
                bytes[i++] = b;
            }
            recipe.setImage(bytes);
            recipeRepository.save(recipe);
        } catch (IOException ioe) {
            log.error("Error occurred saving image", ioe);
            ioe.printStackTrace();
        }
    }
}
