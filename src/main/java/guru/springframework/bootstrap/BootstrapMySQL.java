package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by David Brennan, 29/07/2019, 18:58
 *
 * This is intended to ensure that the minimum data are present in MySQL when we start up.
 * Unlike the @Recipes bootstrap class, this one does not create any recipes.
 *
 * @author edavbre
 */
@Component
@Slf4j
@Profile({"dev", "prod"})
public class BootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapMySQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // Ensure categories are present
        if (categoryRepository.count() == 0) {
            log.debug("Loading category data...");
            loadCategories();
        }

        // Ensure UOMs are present
        if (unitOfMeasureRepository.count() == 0) {
            log.debug("Loading UOM data...");
            loadUOMs();
        }
    }

    private void loadCategories() {
        Category category1 = new Category();
        category1.setDescription("American");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category1.setDescription("Italian");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category1.setDescription("Mexican");
        categoryRepository.save(category3);

        Category category4 = new Category();
        category1.setDescription("Fast Food");
        categoryRepository.save(category4);
    }

    private void loadUOMs() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom1.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom1.setDescription("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom1.setDescription("Spoon");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom1.setDescription("Ounce");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom1.setDescription("Pint");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom1.setDescription("Pinch");
        unitOfMeasureRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom1.setDescription("Each");
        unitOfMeasureRepository.save(uom8);

        UnitOfMeasure uom9 = new UnitOfMeasure();
        uom1.setDescription("Dash");
        unitOfMeasureRepository.save(uom9);
    }
}