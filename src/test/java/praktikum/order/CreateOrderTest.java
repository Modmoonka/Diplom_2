package praktikum.order;
/*
Создание заказа:
-с авторизацией,
-без авторизации,
-с ингредиентами,
-без ингредиентов,
-с неверным хешем ингредиентов.
 * */

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.ingredients.Ingredients;
import praktikum.ingredients.IngredientsClient;
import praktikum.ingredients.IngredientsResponsed;
import praktikum.orders.*;
import praktikum.users.UserClient;
import praktikum.users.UserResponsed;
import praktikum.users.Users;
import java.util.Arrays;
import java.util.List;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;


public class CreateOrderTest {
    private UserClient userClient;
    private String accessToken;
    private Users user;
    private IngredientsClient ingredientsClient;
    private OrderClient orderClient;
    private List<Ingredients> ingredient;

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
        ingredient = ingredientsResponse.getData();
    }

    @After
    public void tearDown() {
        if (accessToken!= null){
            userClient.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Create new order with ingredients and autorization")
    public void createOrderWithAutorithation() {
        List<String> ingredients = ingredientsClient.getRandomIngredients(ingredient, 3);
        OrderRequest orderIngredients = new OrderRequest(ingredients);
        Response response = orderClient.createOrder(orderIngredients, accessToken);
        OrderResponse orderResponse = response.as(OrderResponse.class);
        assertEquals("Incorrect statusCode", SC_OK, response.statusCode());
        assertTrue("Incorrect field success", orderResponse.isSuccess());
    }


    @Test
    @DisplayName("Create new order with autorization without ingredients")
    public void createOrderWithoutIngredients() {
        List<String> orderIngredients = ingredientsClient.getRandomIngredients(ingredient, 0);
        OrderRequest order = new OrderRequest(orderIngredients);
        Response response = orderClient.createOrder(order, accessToken);
        OrderResponse orderResponse = response.as(OrderResponse.class);
        assertEquals("Incorrect statusCode", SC_BAD_REQUEST, response.statusCode());
        assertFalse("Incorrect field success", orderResponse.isSuccess());
        assertEquals("Incorrect message", "Ingredient ids must be provided", orderResponse.getMessage());
    }

    @Test
    @DisplayName("Create new order with autorization with incorrect ingredients hash")
    public void createOrderWithIncorrectIngredientsHash() {
        List<String> orderIngredients = Arrays.asList(randomAlphanumeric(15), randomAlphanumeric(15));
        OrderRequest order = new OrderRequest(orderIngredients);
        Response response = orderClient
                .createOrder(order, accessToken);
        assertEquals("Incorrect statusCode", SC_INTERNAL_SERVER_ERROR, response.statusCode());
    }

    @Test
    @DisplayName("Create new order without autorization")
    public void createOrderwithoutAutorization() {
        List<String> orderIngredients = ingredientsClient.getRandomIngredients(ingredient, 3);
        OrderRequest order = new OrderRequest(orderIngredients);
        Response response = orderClient
                .createOrder(order, "");
        OrderResponse orderResponse = response.as(OrderResponse.class);
        assertEquals("Incorrect statusCode", SC_OK, response.statusCode());
        assertTrue("Incorrect field success", orderResponse.isSuccess());
    }

    @Test
    @DisplayName("Create new order without autorization")
    public void createOrderwithoutAutorizationAndIngredients() {
        List<String> orderIngredients = ingredientsClient.getRandomIngredients(ingredient, 0);
        OrderRequest order = new OrderRequest(orderIngredients);
        Response response = orderClient
                .createOrder(order, "");
        OrderResponse orderResponse = response.as(OrderResponse.class);
        assertEquals("Incorrect statusCode", SC_BAD_REQUEST, response.statusCode());
        assertFalse("Incorrect field success", orderResponse.isSuccess());
    }
}
