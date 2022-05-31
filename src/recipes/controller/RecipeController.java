package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.model.Recipe;
import recipes.repository.map.MapRecipeRepository;

@RestController
public class RecipeController {

    MapRecipeRepository mapRecipeRepository;

    @Autowired
    public RecipeController(MapRecipeRepository mapRecipeRepository) {
        this.mapRecipeRepository = mapRecipeRepository;
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<?> postRecipe(@RequestBody Recipe recipe){
        Integer id = mapRecipeRepository.getNewId();
        mapRecipeRepository.addRecipe(id, recipe);
        String response = "{\"id\":" +
                id +
                "}";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Integer id){
        if(mapRecipeRepository.isRecipeExists(id)) {
            return new ResponseEntity<>(mapRecipeRepository.getRecipeById(id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
