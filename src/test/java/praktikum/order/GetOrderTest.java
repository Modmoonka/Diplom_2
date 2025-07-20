package praktikum.order;

/*
Получение заказов конкретного пользователя:
-авторизованный пользователь,
-неавторизованный пользователь.
 * */

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.ingredients.Ingredients;
import praktikum.ingredients.IngredientsClient;
import praktikum.ingredients.IngredientsResponsed;
import praktikum.orders.OrderClient;
import praktikum.orders.OrderRequest;
import praktikum.users.UserClient;
import praktikum.users.UserResponsed;
import praktikum.users.Users;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;

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
        ingredients = ingredientsResponse.getMassage();

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
    @DisplayName("Получение списка заказов пользователя. С авторизацией")
    public void getUserOrdersWithToken() {
        Response response = orderClient
                .getUserOrders(accessToken);
        OrderListResponse orderListResponse = response.as(OrderListResponse.class);

        assertEquals("Некорректный код ответа", SC_OK, response.statusCode());
        assertTrue("Некорректное поле success", orderListResponse.isSuccess());
        assertNotNull("Список заказов не должен быть пуст", orderListResponse.getOrders());

    }

    @Test
    @DisplayName("Получение списка заказов пользователя. Без авторизации")
    @Description("Тест проверяет что неавториованный пользователь не может получить список заказов")
    public void getUserOrdersWithoutToken() {
        Response response = orderClient
                .getUserOrders("");
        OrderListResponse orderListResponse = response.as(OrderListResponse.class);

        assertEquals("Некорректный код ответа", SC_UNAUTHORIZED, response.statusCode());
        assertFalse("Некорректное поле success", orderListResponse.isSuccess());
        assertEquals("Некорректное сообщение об ошибке", "You should be authorised", orderListResponse.getMessage());

    }

}
