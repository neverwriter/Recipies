package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.service.RecipeService;
import recipes.service.model.Recipe;


/**
 * Method to manage adding and retrieving <code>Recipes</code>
 * @author Patryk Lewczuk
 */
@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> postRecipe(@RequestBody Recipe recipe){

        Recipe recipeToSave = new Recipe(recipe.getName(), recipe.getDescription(), recipe.getIngredients(), recipe.getDirections());
        recipeService.save(recipeToSave);

        String response = "{\"id\":" + recipeToSave.getId() + "}";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Integer id){
        if(recipeService.existsById(id)) {
            return new ResponseEntity<>(recipeService.findRecipeById(id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe (@PathVariable Integer id){
        if(recipeService.existsById(id)) {
            recipeService.deleteRecipeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
