package praktikum.ingredients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static io.restassured.RestAssured.given;
import static praktikum.EnvConfig.BASE_URL;
import static praktikum.EnvURL.URL_INGREDIENTS;

public class IngredientsClient {
    @Step("Getting ingredients data")
    public Response getAllIngredients() {
        return (Response) given()
                .baseUri(BASE_URL)
                .when()
                .get(URL_INGREDIENTS);
    }


    @Step("Getting random ingredients")
    public List<String> getRandomIngredients(List<Ingredients> ingredients, int count) {
        List<Ingredients> randomIngredients = new ArrayList<>(ingredients);
        Collections.shuffle(randomIngredients);
        List<String> random = new ArrayList<>();
        for (int i = 0; i < count && i < randomIngredients.size(); i++) {
            random.add(randomIngredients.get(i).get_id());
        }
        return random;
    }
}
