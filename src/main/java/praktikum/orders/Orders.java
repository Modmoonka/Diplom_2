package praktikum.orders;

import lombok.Getter;
import lombok.Setter;
import praktikum.ingredients.Ingredients;
import java.util.List;

public class Orders {
    @Getter @Setter
    private int number;
    @Getter @Setter
    List<Ingredients> ingredients;
    @Getter @Setter
    String _id;
    @Getter @Setter
    Object owner;
    @Getter @Setter
    String status;
    @Getter @Setter
    String name;
    @Getter @Setter
    private String createdAt;
    @Getter @Setter
    private String updatedAt;
    @Getter @Setter
    private String price;

    public Orders() {}

    public Orders(int number) {
        this.number = number;
    }
}
