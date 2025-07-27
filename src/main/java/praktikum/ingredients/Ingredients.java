package praktikum.ingredients;

import lombok.Data;

@Data
public class Ingredients {
    String _id;
    String name;
    String type;
    float proteins;
    float fat;
    float carbohydrates;
    float calories;
    float price;
    String image;
    String image_mobile;
    String image_large;
    int __v;

    public Ingredients() {}

    public Ingredients(String id, String name, String type, float proteins, float fat,
                      float carbohydrates, float calories, float price, String image,
                      String image_mobile, String image_large, int __v) {
        this._id = id;
        this.name = name;
        this.type = type;
        this.proteins = proteins;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.price = price;
        this.image = image;
        this.image_mobile = image_mobile;
        this.image_large = image_large;
        this.__v = __v;
    }
}
