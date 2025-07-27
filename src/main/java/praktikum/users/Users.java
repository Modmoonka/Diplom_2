package praktikum.users;

import lombok.Data;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class Users {
    private String email;
    private String password;
    private String name;

    public Users() {}

    public Users(String email, String password, String nameUser) {
        this.email = email;
        this.password = password;
        this.name = nameUser;
    }

    public static Users random() {
        int suffix = ThreadLocalRandom.current().nextInt(100, 100_000);
        return new Users("Jack" + suffix + "@ya.ru", "P@ssw0rd123", "Sparrow");
    }
}