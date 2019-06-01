package guru.springframework.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by David Brennan, 01/06/2019, 13:59
 *
 * @author EDAVBRE
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public void saveImageFile(Long recipeId, MultipartFile image) {
        // TODO - persist the image
        log.info("Received an image file");
    }
}
