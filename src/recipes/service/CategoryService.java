package recipes.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import recipes.repository.CategoryRepository;
import recipes.service.model.Category;

import java.util.Collections;
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

    public Integer getId (String category) {
        return categoryRepository.findByCategoryName(category).get().getId();
    }

    public List<?> findAllByCategory(String category){

    Category fetchCategory = categoryRepository.findByCategoryName(category).orElse(null);

    if (fetchCategory != null) {
            return fetchCategory.getRecipes();
        }
         return Collections.EMPTY_LIST;

    }
}
