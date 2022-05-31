package recipes.repository.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapRecipeRepository implements RecipeRepository {

    private final ConcurrentHashMap<Integer, Recipe> mapRecipeDataBase;

    @Autowired
    public MapRecipeRepository(ConcurrentHashMap<Integer, Recipe> mapRecipeDataBase) {
        this.mapRecipeDataBase = mapRecipeDataBase;
    }

    @Override
    public void addRecipe(Integer id, Recipe recipe) {
        mapRecipeDataBase.put(id, recipe);
    }

    @Override
    public Recipe getRecipeById(Integer id) {
        return mapRecipeDataBase.get(id);
    }

    @Override
    public Integer getNewId() {
        if(mapRecipeDataBase.size() < 1){
            return mapRecipeDataBase.size()+1;
        }

        Optional<Map.Entry<Integer, Recipe>> max = mapRecipeDataBase
                .entrySet()
                .stream().max(Map.Entry.comparingByKey());

        return max.get().getKey()+1;
    }

    @Override
    public boolean isRecipeExists(Integer id) {
        return mapRecipeDataBase.containsKey(id);
    }
}
