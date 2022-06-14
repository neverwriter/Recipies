package recipes.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.service.model.Category;
import recipes.service.model.Recipe;
import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

  Recipe findRecipeById(Integer id);

  @Query("select r from Recipe r where r.category = ?1")
  List<Recipe> findRecipesByCategory(Category category);

  List<Recipe> findByNameContainingIgnoreCase(String name);

  Recipe getOne(Integer id);
}
