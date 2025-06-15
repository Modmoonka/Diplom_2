package praktikum.users;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static praktikum.EnvConfig.BASE_URL;
import static praktikum.EnvURL.*;

public class UserClient {
    @Step("Create new user")
    public Response register(Users user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(URL_USER_REGISTER);
    }

    @Step("User login")
    public Response login(Users user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(URL_USER_LOGIN);
    }

    @Step("Change user date")
    public Response update(Users user, String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Authorization", accessToken)
                .body(user)
                .when()
                .patch(URL_USER);
    }

    @Step("User delete")
    public void delete(String accessToken) {
        given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .delete(URL_USER);
    }
}
