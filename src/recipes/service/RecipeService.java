package recipes.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.repository.RecipeRepository;
import recipes.service.model.Recipe;

@Service
@Getter
@Setter
@NoArgsConstructor
public class RecipeService {

    RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void save(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public Recipe findRecipeById(Integer id){
        return recipeRepository.findRecipeById(id);
    }

    public boolean existsById(Integer id){
        return recipeRepository.existsById(id);
    }
}
