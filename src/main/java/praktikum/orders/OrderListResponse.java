package praktikum.orders;

import java.util.List;
import lombok.Data;

@Data
public class OrderListResponse {
    private boolean success;
    private List<OrdersList> orders;
    private String message;
    private int total;
    private int totalToday;

    public OrderListResponse() {}

    public OrderListResponse(boolean success, List<OrdersList> orders, String message, int total, int totalToday) {
        this.success = success;
        this.orders = orders;
        this.message = message;
        this.total = total;
        this.totalToday = totalToday;
    }

    public OrderListResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
