package recipes.service.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Custom serialization class to change display of category field
 * @author Patryk Lewczuk
 */
public class RecipeSerializer extends StdSerializer<Recipe> {

    public RecipeSerializer(){
        this(null);
    }

    public RecipeSerializer(Class<Recipe> recipeClass){
        super(recipeClass);
    }

    @Override
    public void serialize(Recipe value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", value.getName());
        gen.writeStringField("category", value.getCategory().getCategory());
        gen.writeObjectField("date", value.getDate());
        gen.writeStringField("description", value.getDescription());
        gen.writeObjectField("ingredients", value.getIngredients());
        gen.writeObjectField("directions", value.getDirections());
        gen.writeEndObject();
    }
}
