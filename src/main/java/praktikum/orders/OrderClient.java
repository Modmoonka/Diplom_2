package praktikum.orders;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static praktikum.EnvConfig.BASE_URL;
import static praktikum.EnvURL.URL_ORDERS;

public class OrderClient {
    @Step("Create new order")
    public Response createOrder(OrderRequest order, String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .header("Authorization", accessToken)
                .body(order)
                .when()
                .post(URL_ORDERS);
    }

    @Step("Create new order without authorization")
    public Response createOrderUnauthorized(OrderRequest order) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(URL_ORDERS);
    }

    @Step("Get user orders")
    public Response getUserOrders(String accessToken) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .get(URL_ORDERS);
    }
}
