package recipes.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "category_id")
    private Integer id;

    @Column
    @NotBlank
    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Recipe> recipes = new ArrayList<>();

    public Category(String category) {
        this.category = category;
    }
}
