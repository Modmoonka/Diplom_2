package praktikum.users;

import lombok.Data;

@Data
public class UserResponsed {
    private boolean success;
    private Users user;
    private String accessToken;
    private String refreshToken;
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
