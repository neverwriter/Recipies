package recipes.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recipes.model.Recipe;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public ConcurrentHashMap<Integer, Recipe> mapRecipeDataBase(){
        return new ConcurrentHashMap<>();
    }
}
