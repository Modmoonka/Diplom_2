package praktikum.orders;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private List<String> ingredients;

    public OrderRequest() {}

    public OrderRequest(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
