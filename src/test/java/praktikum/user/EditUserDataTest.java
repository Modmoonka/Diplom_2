package praktikum.user;
/*
Изменение данных пользователя:
с авторизацией,
без авторизации,
Для обеих ситуаций нужно проверить, что любое поле можно изменить. Для неавторизованного пользователя — ещё и то, что система вернёт ошибку.
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

public class EditUserDataTest {
    private UserClient userClient;
    Users user;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        accessToken = null;
        user = Users.random();
        UserResponsed responseRegister = userClient.register(user)
                .then().statusCode(SC_OK).extract().as(UserResponsed.class);
        accessToken = responseRegister.getAccessToken();
    }


    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Edit user email with authorization")
    public void GetUserEmailWithAuthorization() {
        user.setEmail("Loveis@ya.mir");
        Response updateResponse = userClient.update(user, accessToken);
        assertEquals("Incorrect response", SC_FORBIDDEN, updateResponse.statusCode());
        accessToken = updateResponse.as(UserResponsed.class).getAccessToken();
        assertFalse("Incorrect field success", updateResponse.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Edit user name with authorization")
    public void GetUserNameWithAuthorization() {
        user.setName("Bublegum");
        Response updateResponse = userClient.update(user, accessToken);
        assertEquals("Incorrect response", SC_OK, updateResponse.statusCode());
        accessToken = updateResponse.as(UserResponsed.class).getAccessToken();
        assertTrue("Incorrect field success", updateResponse.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Edit user password with authorization")
    public void GetUserPasswordWithAuthorization() {
        user.setPassword("BublegumLoveIs");
        Response updateResponse = userClient.update(user, accessToken);
        assertEquals("Incorrect response", SC_OK, updateResponse.statusCode());
        accessToken = updateResponse.as(UserResponsed.class).getAccessToken();
        assertTrue("Incorrect field success", updateResponse.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Edit user password without authorization")
    public void GetUserPasswordWithoutAuthorization() {
        user.setPassword("BublegumLoveIs");
        Response updateResponse = userClient.update(user, "");
        assertEquals("Incorrect response", SC_UNAUTHORIZED, updateResponse.statusCode());
        accessToken = updateResponse.as(UserResponsed.class).getAccessToken();
        assertFalse("Incorrect field success", updateResponse.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Edit user name without authorization")
    public void GetUserNameWithoutAuthorization() {
        user.setName("Bublegum");
        Response updateResponse = userClient.update(user, "");
        assertEquals("Incorrect response", SC_UNAUTHORIZED, updateResponse.statusCode());
        accessToken = updateResponse.as(UserResponsed.class).getAccessToken();
        assertFalse("Incorrect field success", updateResponse.as(UserResponsed.class).isSuccess());
    }

    @Test
    @DisplayName("Edit user email without authorization")
    public void GetUserEmailWithoutAuthorization() {
        user.setEmail("Loveis@ya.mir");
        Response updateResponse = userClient.update(user, "");
        assertEquals("Incorrect response", SC_UNAUTHORIZED, updateResponse.statusCode());
        accessToken = updateResponse.as(UserResponsed.class).getAccessToken();
        assertFalse("Incorrect field success", updateResponse.as(UserResponsed.class).isSuccess());
    }
}
