package praktikum.orders;

import lombok.Data;
import java.util.List;

@Data
public class OrdersList {
    String _id;
    List<String> ingredients;
    String status;
    String name;
    private int number;
    private String createdAt;
    private String updatedAt;

    public OrdersList(){}

    public OrdersList(String _id, List<String> ingredients, String status, String name, int number, String createdAt, String updatedAt){
        this._id = _id;
        this.ingredients = ingredients;
        this.status = status;
        this.name = name;
        this.number = number;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
