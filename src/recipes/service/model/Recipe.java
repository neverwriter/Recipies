package recipes.service.model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = RecipeSerializer.class)
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Integer id;

    private LocalDateTime date;

    @Column
    @NotBlank
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    @NotBlank
    private String description;

    @ElementCollection
    @CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name="ingredient")
    @Size(min=1)
    private List<@NotBlank String> ingredients;

    @ElementCollection
    @CollectionTable(name = "directions", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name="direction")
    @Size(min=1)
    private List<@NotBlank String> directions;


    public Recipe(String name, String description, List<String> ingredients, List<String> directions, Category category) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        this.category = category;
        this.date = LocalDateTime.now();
    }
}
