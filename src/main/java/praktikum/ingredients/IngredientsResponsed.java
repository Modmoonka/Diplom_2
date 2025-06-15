package praktikum.ingredients;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class IngredientsResponsed {
    public String success;
    @Getter @Setter
    public List<Ingredients> massage;

    public IngredientsResponsed() { }

    public IngredientsResponsed(String success, List<Ingredients> massage) {
        this.success = success;
        this.massage = massage;
    }
}
