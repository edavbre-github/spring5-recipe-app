package guru.springframework.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by David Brennan, 01/06/2019, 13:45
 *
 * @author EDAVBRE
 */
public interface ImageService{
    public void saveImageFile(Long recipeId, MultipartFile image);
}
