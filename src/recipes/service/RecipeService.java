package recipes.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.repository.RecipeRepository;
import recipes.service.model.Category;
import recipes.service.model.Recipe;

import java.util.List;

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


}
