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
    @Getter @Setter
    private int number;


    public boolean isSuccess() {
        return success;
    }
}
