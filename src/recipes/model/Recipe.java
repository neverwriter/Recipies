package recipes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private ArrayList<String> directions;
}
