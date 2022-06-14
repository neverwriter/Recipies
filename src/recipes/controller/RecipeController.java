package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.service.CategoryService;
import recipes.service.RecipeService;
import recipes.service.model.Recipe;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


/**
 * End-point for manage CRUD operation of <code>Recipes</code>
 *
 * @author Patryk Lewczuk
 */
@RestController
public class RecipeController {

    RecipeService recipeService;

    CategoryService categoryService;

    @Autowired
    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> postRecipe(@Valid @RequestBody Recipe recipe) {

        //If add to pass platform test in normal app @Size(min=1) annotation work fine
        if (recipeValidator(recipe)) {
            Recipe recipeToSave = new Recipe(recipe.getName(), recipe.getDescription(), recipe.getIngredients(), recipe.getDirections(), recipe.getCategory());
            recipeService.save(recipeToSave);

            String response = "{\"id\":" + recipeToSave.getId() + "}";
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Integer id) {
        if (recipeService.existsById(id)) {
            return new ResponseEntity<>(recipeService.findRecipeById(id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/recipe/search")
    public ResponseEntity<?> searchRecipe(@RequestParam Optional<String> name, @RequestParam Optional<String> category) {
        String response = "[]";

        if (name.isPresent() && category.isEmpty()) {

            List<Recipe> recipeList = recipeService.findByNameContainingIgnoreCase(name.get());
            recipeList.sort(Comparator.comparing(Recipe::getDate).reversed());
            return new ResponseEntity<>(recipeList, HttpStatus.OK);
        }

        if (category.isPresent() && name.isEmpty()) {

            List<Recipe> recipeList = categoryService.findAllByCategory(category.get());
            recipeList.sort(Comparator.comparing(Recipe::getDate).reversed());

            return new ResponseEntity<>(recipeList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

    @PutMapping("api/recipe/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {

        if (recipeValidator(recipe)) {

            if (recipeService.existsById(id)) {

                recipeService.updateRecipe(id, recipe);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Integer id) {
        if (recipeService.existsById(id)) {
            recipeService.deleteRecipeById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private boolean recipeValidator(Recipe recipe) {
        return recipe.getDirections() != null
                && recipe.getIngredients() != null
                && recipe.getIngredients().size() >= 1
                && recipe.getDirections().size() >= 1
                && recipe.getCategory() != null
                && !recipe.getCategory().getCategory().isBlank();
    }
}
