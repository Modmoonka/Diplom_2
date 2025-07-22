package praktikum.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OrderResponse {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Orders order;
    @Getter @Setter
    private boolean success;
    @Getter @Setter
    private String message;

    public OrderResponse() {}

    public OrderResponse(String name, Orders order, boolean success) {
        this.success = success;
        this.order = order;
        this.name = name;
    }

    public OrderResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}


