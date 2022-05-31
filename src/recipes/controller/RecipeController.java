package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.model.Recipe;

import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
public class RecipeController {

    ConcurrentLinkedQueue<Recipe> recipes = new ConcurrentLinkedQueue<>();

    @PostMapping("/api/recipe")
    public void postRecipe(@RequestBody Recipe recipe){
        recipes.clear();
        recipes.add(recipe);
    }

    @GetMapping("/api/recipe")
    public ResponseEntity<?> getRecipe(){
        return new ResponseEntity<>(recipes.peek(), HttpStatus.OK);
    }
}
