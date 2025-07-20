package praktikum.ingredients;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class IngredientsResponsed {
    @Setter
    public boolean success;
    @Getter @Setter
    public List<Ingredients> data;

    public IngredientsResponsed() { }

    public IngredientsResponsed(boolean success, List<Ingredients> data) {
        this.success = success;
        this.data = data;
    }

    public boolean success() {
        return success;
    }
}
