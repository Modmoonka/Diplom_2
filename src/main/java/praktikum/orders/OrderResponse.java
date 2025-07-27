package praktikum.orders;

import lombok.Data;

@Data
public class OrderResponse {
    private String name;
    private Orders order;
    private boolean success;
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


