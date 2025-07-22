package praktikum.user;

/*
 Логин пользователя:
- логин под существующим пользователем
- логин с неверным логином и паролем.
 * */

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.users.UserClient;
import praktikum.users.UserResponsed;
import praktikum.users.Users;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class LoginUserTest {
    UserClient userClient = new UserClient();
    Users user;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        accessToken = null;
        user = Users.random();
        Response responseRegister = userClient.register(user);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Login user")
    public void LoginUser() {
        Response responseLogin = userClient.login(user);
        assertEquals("Incorrect response", SC_OK, responseLogin.statusCode());
        accessToken = responseLogin.as(UserResponsed.class).getAccessToken();
        assertTrue("Incorrect field success", responseLogin.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Login user with incorrect password")
    public void UserIncorrectPassword() {
        user.setPassword("2134hhhlflkjh");
        Response responseLogin = userClient.login(user);
        assertEquals("Incorrect response", SC_UNAUTHORIZED, responseLogin.statusCode());
        accessToken = responseLogin.as(UserResponsed.class).getAccessToken();
        assertFalse("Incorrect field success", responseLogin.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Login user with incorrect login")
    public void UserIncorrectEmail() {
        user.setEmail("lumbuba@ya.com");
        Response responseLogin = userClient.login(user);
        assertEquals("Incorrect response", SC_UNAUTHORIZED, responseLogin.statusCode());
        accessToken = responseLogin.as(UserResponsed.class).getAccessToken();
        assertFalse("Incorrect field success", responseLogin.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Login user with incorrect login")
    public void UserIncorrectEmailAndPassword() {
        user.setEmail("lumbuba@ya.com");
        user.setPassword("lumbuba2025.net");
        Response responseLogin = userClient.login(user);
        assertEquals("Incorrect response", SC_UNAUTHORIZED, responseLogin.statusCode());
        accessToken = responseLogin.as(UserResponsed.class).getAccessToken();
        assertFalse("Incorrect field success", responseLogin.as(UserResponsed.class).isSuccess());
    }
}
