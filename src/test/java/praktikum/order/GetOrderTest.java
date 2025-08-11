package praktikum.order;

/*
Получение заказов конкретного пользователя:
-авторизованный пользователь,
-неавторизованный пользователь.
 * */

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.ingredients.Ingredients;
import praktikum.ingredients.IngredientsClient;
import praktikum.ingredients.IngredientsResponsed;
import praktikum.orders.OrderClient;
import praktikum.orders.OrderListResponse;
import praktikum.orders.OrderRequest;
import praktikum.users.UserClient;
import praktikum.users.UserResponsed;
import praktikum.users.Users;
import java.util.List;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class GetOrderTest {
    private UserClient userClient;
    private String accessToken;
    private List<Ingredients> ingredients;
    private Users user;
    private IngredientsClient ingredientsClient;
    private OrderClient orderClient;

    @Before
    public void setUp() {
        userClient = new UserClient();
        ingredientsClient = new IngredientsClient();
        orderClient = new OrderClient();
        accessToken = null;
        user = Users.random();
        UserResponsed regResponse = userClient.register(user)
                .then().statusCode(SC_OK).extract().as(UserResponsed.class);
        accessToken = regResponse.getAccessToken();

        IngredientsResponsed ingredientsResponse = ingredientsClient.getAllIngredients()
                .then().statusCode(SC_OK).extract().as(IngredientsResponsed.class);
        ingredients = ingredientsResponse.getData();

        List<String> orderIngredients = ingredientsClient.getRandomIngredients(ingredients, 3);
        OrderRequest order = new OrderRequest(orderIngredients);
        orderClient.createOrder(order, accessToken)
                .then().statusCode(SC_OK);
    }

    @After
    public void tearDown() {
        if (accessToken!= null){
            userClient.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Getting orders with authorization")
    public void getOrdersWithAutorithation() {
        Response response = orderClient.getUserOrders(accessToken);
        OrderListResponse OrderListResponse = response.as(OrderListResponse.class);
        assertEquals("Incorrect statusCode", SC_OK, response.statusCode());
        assertTrue("Incorrect field success", OrderListResponse.isSuccess());
        assertNotNull("The order list must not be empty", OrderListResponse.getOrders());
    }

    @Test
    @DisplayName("Getting a list of orders without authorization")
    public void getOrdersWithoutAutorithation() {
        Response response = orderClient.getUserOrders("");
        OrderListResponse OrderListResponse = response.as(OrderListResponse.class);
        assertEquals("Incorrect statusCode", SC_UNAUTHORIZED, response.statusCode());
        assertFalse("Incorrect field success", OrderListResponse.isSuccess());
        assertEquals("Incorrect error message", "You should be authorised", OrderListResponse.getMessage());
    }
}
