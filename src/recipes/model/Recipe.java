package recipes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    private String name;
    private String description;
    private String ingredients;
    private String directions;
}
