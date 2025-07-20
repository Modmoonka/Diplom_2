package praktikum.orders;

import lombok.Getter;
import lombok.Setter;


public class Orders {
    @Getter @Setter
    private int number;

    public Orders(){
    }

    public Orders(int number) {
        this.number = number;
    }
}
