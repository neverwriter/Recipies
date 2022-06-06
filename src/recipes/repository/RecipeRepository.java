package recipes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.service.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

  Recipe findRecipeById(Integer id);
}
