package praktikum.ingredients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.net.HttpURLConnection;
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
        List<Ingredients> shuffled = new ArrayList<>(ingredients);
        Collections.shuffle(shuffled);

        List<String> randomIds = new ArrayList<>();
        for (int i = 0; i < count && i < shuffled.size(); i++) {
            randomIds.add(shuffled.get(i).get_id());
        }
        return randomIds;
    }
}
