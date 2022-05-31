package recipes.repository;

import recipes.model.Recipe;

public interface RecipeRepository {

    void addRecipe(Integer id, Recipe recipe);

    Recipe getRecipeById(Integer id);

    Integer getNewId();

    boolean isRecipeExists(Integer id);
}
