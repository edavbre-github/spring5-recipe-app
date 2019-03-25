package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by David Brennan, 05/03/2019, 18:15 (copied from Pet Clinic)
 *
 * @author edavbre
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    /**
     * Using constructor based injection here, and because this class is already marked as a Controller/Component,
     * Spring will Autowire these in for us.
     *
     * @param categoryRepository
     * @param unitOfMeasureRepository
     */
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index() {
        System.out.println("Dummy message fom Dagger zzzzzzzzz");

        // Simple test of our repositories...
        Optional<Category> optionalCategory = categoryRepository.findByDescription("Mexican");
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Spoon");

        System.out.println("Category ID: " + optionalCategory.get().getId());
        System.out.println("UOM ID: " + optionalUnitOfMeasure.get().getId());

        // Causes Thymeleaf to lookup index.html
        return "index";
    }
}
