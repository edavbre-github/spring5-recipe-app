package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by David Brennan, 05/03/2019, 18:15 (copied from Pet Clinic)
 *
 * @author edavbre
 */
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(Model model) {
        //System.out.println("Dummy message fom Dagger zzzzzzzzz");

        model.addAttribute("recipes", recipeService.getRecipes());

        // Causes Thymeleaf to lookup index.html
        return "index";
    }
}
