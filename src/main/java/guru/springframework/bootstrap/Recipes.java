package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by David Brennan, 26/03/2019, 18:11
 *
 * @author edavbre
 */
@Component
public class Recipes implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public Recipes(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    /**
     * This is pretty ugly shit, but f*ck it!!
     *
     * @return
     */
    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        // Find the needed units of measure...
        UnitOfMeasure eachUnitOfMeasure = findUomByDesc("Each").get();
        UnitOfMeasure tablespoonUnitOfMeasure = findUomByDesc("Tablespoon").get();
        UnitOfMeasure teaspoonUnitOfMeasure = findUomByDesc("Teaspoon").get();
        UnitOfMeasure dashUnitOfMeasure = findUomByDesc("Dash").get();
        UnitOfMeasure pintUnitOfMeasure = findUomByDesc("Pint").get();
        UnitOfMeasure cupUnitOfMeasure = findUomByDesc("Cup").get();

        // Find the needed categories...
        Category americanCat = findCategoryByDesc("American").get();
        Category mexicanCat = findCategoryByDesc("Mexican").get();

        // Yummy Guacamole recipe...
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1. Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "<a href=\"https://www.simplyrecipes.com/recipes/how_to_cut_and_peel_an_avocado/\">(See How to Cut and Peel an Avocado.)</a>. Place in a bowl.\n\n" +
                "2. Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n\n" +
                "3. Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. " +
                "The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. " +
                "So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n\n" +
                "4. Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. " +
                "(The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        // Set the ingredients
        guacRecipe.getIngredients().add(new Ingredient("Ripe Avocados", new BigDecimal(2), eachUnitOfMeasure, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Kosher Salt", new BigDecimal(0.5), teaspoonUnitOfMeasure, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Fresh Lime juice or Lemon juice", new BigDecimal(1), tablespoonUnitOfMeasure, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Minced Red Onion or thinly sliced Green Onion", new BigDecimal(2), tablespoonUnitOfMeasure, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Serrano Chiles, stems and seeds removed, minced", new BigDecimal(2), eachUnitOfMeasure, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoonUnitOfMeasure, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Freshly grated Black Pepper", new BigDecimal(1), dashUnitOfMeasure, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Ripe Tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), eachUnitOfMeasure, guacRecipe));

        guacRecipe.getCategories().add(americanCat);
        guacRecipe.getCategories().add(mexicanCat);

        // Recipe complete, add it to the list
        recipes.add(guacRecipe);

        // Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. " +
                "Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. " +
                "Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. " +
                "As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. " +
                "Drizzle with the thinned sour cream. Serve with lime wedges.\n\n" +
                "Read More: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. " +
                "I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. " +
                "You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. " +
                "Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n");
        tacoNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacoNotes);

        // Ingredients...
        tacosRecipe.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoonUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoonUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaspoonUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Salt", new BigDecimal(0.5), teaspoonUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Garlic, finely chopped", new BigDecimal(1), eachUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Finely grated Orange zest", new BigDecimal(1), tablespoonUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Fresh-squeezed Orange juice", new BigDecimal(3), tablespoonUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Skinless, boneless Chicken Thighs", new BigDecimal(4), eachUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Corn Tortillas, small", new BigDecimal(8), eachUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Packed Baby Arugula", new BigDecimal(3), cupUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Medium ripe Avocados", new BigDecimal(2), eachUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Radishes, thinly sliced", new BigDecimal(4), eachUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Cherry Tomatoes, halved", new BigDecimal(0.5), pintUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Red Onion, thinly sliced", new BigDecimal(0.25), eachUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Roughly chopped Cilantro", new BigDecimal(1), eachUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Sour Cream thinned with 1/4 cup milk", new BigDecimal(0.5), cupUnitOfMeasure, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Lime, cut into wedges", new BigDecimal(1), eachUnitOfMeasure, tacosRecipe));

        tacosRecipe.getCategories().add(americanCat);
        tacosRecipe.getCategories().add(mexicanCat);

        // Recipe complete, add it to the list
        recipes.add(tacosRecipe);

        return recipes;
    }

    /**
     * Get the required unit of measure.
     * @param desc
     * @return Optional\<UnitOfMeasure\>
     */
    private Optional<UnitOfMeasure> findUomByDesc(String desc) {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription(desc);
        if (!uomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        return uomOptional;
    }

    /**
     * Get the required category.
     * @param desc
     * @return Optional\<Category\>
     */
    private Optional<Category> findCategoryByDesc(String desc) {
        Optional<Category> categoryOptional = categoryRepository.findByDescription(desc);
        if (!categoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }
        return categoryOptional;
    }
}
