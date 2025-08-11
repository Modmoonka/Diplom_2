package praktikum.orders;

import lombok.Data;
import praktikum.ingredients.Ingredients;
import java.util.List;

@Data
public class Orders {
    private int number;
    List<Ingredients> ingredients;
    String _id;
    Object owner;
    String status;
    String name;
    private String createdAt;
    private String updatedAt;
    private String price;

    public Orders() {}

    public Orders(int number) {
        this.number = number;
    }
}
