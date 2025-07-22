package praktikum.users;

import lombok.Getter;
import lombok.Setter;

public class UserResponsed {
    @Getter @Setter
    private boolean success;
    @Getter @Setter
    private Users user;
    @Getter @Setter
    private String accessToken;
    @Getter @Setter
    private String refreshToken;
    @Getter @Setter
    private String message;

    public UserResponsed() {}

    public UserResponsed(boolean success, Users user, String accessToken, String refreshToken) {
        this.success = success;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public UserResponsed(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
