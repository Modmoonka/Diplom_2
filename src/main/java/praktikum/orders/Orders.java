package praktikum.orders;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class Orders {
    @Getter
    @Setter
    List<String> ingredients;

    public Orders () {
    }

    public Orders(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
