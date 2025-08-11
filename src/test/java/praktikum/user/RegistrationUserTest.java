package praktikum.user;

/*
создать уникального пользователя;
создать пользователя, который уже зарегистрирован;
создать пользователя и не заполнить одно из обязательных полей.
 * */

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.users.UserClient;
import praktikum.users.UserResponsed;
import praktikum.users.Users;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class RegistrationUserTest {
    UserClient userClient = new UserClient();
    Users user;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        accessToken = null;
    }


    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Registration new user")
    public void aregisterNewUser() {
        user = Users.random();
        Response response = userClient.register(user);
        assertEquals("Incorrect response", SC_OK, response.statusCode());
        accessToken = response.as(UserResponsed.class).getAccessToken();
        assertTrue("Incorrect field success", response.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Registration double user")
    public void registerNewDoubleUser() {
        user = Users.random();
        Response responseCorrectUser = userClient.register(user);
        assertEquals("Incorrect response", SC_OK, responseCorrectUser.statusCode());

        Response responseDoubleUser = userClient.register(user);
        assertEquals("Incorrect response", SC_FORBIDDEN, responseDoubleUser.statusCode());
        accessToken = responseDoubleUser.as(UserResponsed.class).getAccessToken();
        assertFalse("Incorrect field success", responseDoubleUser.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Registration new user without email")
    public void registerNewUserWithoutEmail() {
        user = Users.random();
        user.setEmail(null);
        Response response = userClient.register(user);
        assertEquals("Incorrect response", SC_FORBIDDEN, response.statusCode());
        accessToken = response.as(UserResponsed.class).getAccessToken();
        assertFalse("Incorrect field success", response.as(UserResponsed.class).isSuccess());
    }
}

