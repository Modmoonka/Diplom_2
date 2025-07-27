package praktikum.ingredients;

import lombok.Data;
import java.util.List;

@Data
public class IngredientsResponsed {
    public boolean success;
    public List<Ingredients> data;
    public IngredientsResponsed() { }

    public IngredientsResponsed(boolean success, List<Ingredients> data) {
        this.success = success;
        this.data = data;
    }
}
