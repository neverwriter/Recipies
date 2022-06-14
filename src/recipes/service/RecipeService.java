package recipes.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.repository.RecipeRepository;
import recipes.service.model.Category;
import recipes.service.model.Recipe;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Getter @Setter
@NoArgsConstructor
public class RecipeService {

    RecipeRepository recipeRepository;

    CategoryService categoryService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.recipeRepository = recipeRepository;
    }


    public void save(Recipe recipe){

        if(categoryService.existByCategory(recipe.getCategory().getCategory())){

            recipe.getCategory().setId(categoryService.getId(recipe.getCategory().getCategory()));

        } else {

            categoryService.save(recipe.getCategory());
        }

        recipeRepository.save(recipe);
    }

    public Recipe findRecipeById(Integer id){
        return recipeRepository.findRecipeById(id);
    }

    public boolean existsById(Integer id){
        return recipeRepository.existsById(id);
    }

    public void deleteRecipeById(Integer id){
        recipeRepository.deleteById(id);
    }

    public List<Recipe> findRecipesByCategory(Category category){
       return recipeRepository.findRecipesByCategory(category);
    }

    public List<Recipe> findByNameContainingIgnoreCase(String recipeName){
        return recipeRepository.findByNameContainingIgnoreCase(recipeName);
    }


    public void updateRecipe (Integer id, Recipe recipe){

        Recipe recipeForUpdate = Optional.ofNullable(
                recipeRepository.getOne(id))
                .orElseThrow(() -> new IllegalStateException("recipe with id " + id + " does not exists."));

        recipeForUpdate.setName(recipe.getName());

        if(categoryService.existByCategory(recipe.getCategory().getCategory())){
            recipeForUpdate.setCategory((categoryService.findByCategoryName(recipe.getCategory().getCategory())));
        } else {
            recipeForUpdate.setCategory(recipe.getCategory());
            categoryService.save(recipeForUpdate.getCategory());
        }

        recipeForUpdate.setDescription(recipe.getDescription());
        recipeForUpdate.setDate(LocalDateTime.now());
        recipeForUpdate.setIngredients(recipe.getIngredients());
        recipeForUpdate.setDirections(recipe.getDirections());

        recipeRepository.save(recipeForUpdate);
    }

}
