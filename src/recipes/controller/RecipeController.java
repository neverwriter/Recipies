package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.service.RecipeService;
import recipes.service.model.Recipe;

import javax.validation.Valid;
import java.util.Optional;


/**
 * End-point for manage CRUD operation of <code>Recipes</code>
 * @author Patryk Lewczuk
 */
@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> postRecipe(@Valid @RequestBody Recipe recipe) {

        //If add to pass platform test in normal app @Size(min=1) annotation work fine
        if (recipe.getDirections()!= null
                && recipe.getIngredients() != null
                && recipe.getIngredients().size() >= 1
                && recipe.getDirections().size() >= 1) {
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

        if(name.isPresent() && category.isEmpty()){
            response = name.get();
        return new ResponseEntity<>(response, HttpStatus.OK);
        }

        if(category.isPresent() && name.isEmpty()){
            response = category.get();
            return new ResponseEntity<>(response, HttpStatus.OK);
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
}
