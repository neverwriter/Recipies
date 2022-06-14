package recipes.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.repository.CategoryRepository;
import recipes.service.model.Category;
import recipes.service.model.Recipe;

import java.util.ArrayList;
import java.util.List;


@Service
@Getter @Setter
@NoArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService( CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public boolean existByCategory (String category) {
        return categoryRepository.findByCategoryName(category).isPresent();
    }

    public Category findById(Integer id){
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException("category with id " + id + " does not exists."));
    }

    public Integer getId (String category) {
        return categoryRepository.findByCategoryName(category).get().getId();
    }

    public List<Recipe> findAllByCategory(String category){

    List<Category> categoryList = categoryRepository.findByCategoryIgnoreCase(category);

    List<Recipe> recipeList = new ArrayList<>();

        for (Category fetchedCategory: categoryList) {
            recipeList.addAll(fetchedCategory.getRecipes());
        }

    return recipeList;

    }

    public void save (Category category) {
        categoryRepository.save(category);
    }

    public Category findByCategoryName (String category){
        return categoryRepository.findByCategoryName(category).orElseThrow(() -> new IllegalStateException("category with name " + category + " does not exists."));
    }
}
