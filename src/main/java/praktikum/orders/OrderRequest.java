package praktikum.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OrderRequest {
    @Getter @Setter
    private List<String> ingredients;

    public OrderRequest() {}

    public OrderRequest(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
