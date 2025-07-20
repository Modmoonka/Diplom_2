package praktikum.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OrderList {
    @Getter @Setter
    private List<String> ingredients;
    @Getter @Setter
    private String _id;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private String name;
    private String createdAt;
    private String updatedAt;
    private int number;

    public OrderList(List<String> ingredients, String _id, String status, String name, String createdAt, String updatedAt, int number) {
        this.ingredients = ingredients;
        this._id = _id;
        this.status = status;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.number = number;
    }
}
