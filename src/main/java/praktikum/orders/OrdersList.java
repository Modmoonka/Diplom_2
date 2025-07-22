package praktikum.orders;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class OrdersList {
    @Getter @Setter
    String _id;
    @Getter @Setter
    List<String> ingredients;
    @Getter @Setter
    String status;
    @Getter @Setter
    String name;
    @Getter @Setter
    private int number;
    @Getter @Setter
    private String createdAt;
    @Getter @Setter
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
