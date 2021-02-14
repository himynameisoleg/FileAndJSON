import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

import java.math.BigDecimal;

public class Database {
    private JsonObject restaurants;

    public Database(JsonObject data){
        this.restaurants = data;
    }

    public JsonObject getRestaurant(String name){
        JsonArray restaurantList = (JsonArray) this.restaurants.get("restaurants");
        JsonObject found = new JsonObject();

        for (int i = 0; i < restaurantList.size(); i++) {
            JsonObject match = (JsonObject) restaurantList.get(i);
            String restaurantName = (String) match.get("name");
            if(restaurantName.equals(name)) {
                found = match;
                break;
            }
        }

        return found;
    }

    public double getAvgReviews(String name){
        JsonObject targetRestaurant = this.getRestaurant(name);
        JsonArray reviews = (JsonArray) targetRestaurant.get("reviews");

        int total = 0;

        for (int i = 0; i < reviews.size(); i++) {
            JsonObject current = (JsonObject) reviews.get(i);
            BigDecimal rating = (BigDecimal) current.get("rating");

            total += rating.intValue();
        }

        double result =  (double) total / reviews.size();

        return result;
    }
}
