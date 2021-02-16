import json_simple.JsonArray;
import json_simple.JsonException;
import json_simple.JsonObject;
import json_simple.Jsoner;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class OnlineRecipeDatabase {

    String baseUrl = "http://www.recipepuppy.com/api/?";

    public JsonObject getRecipesByIngredients(String ingredients) throws Exception
    {
        URL url = new URL(baseUrl+"i="+ingredients);
        Scanner stream = new Scanner(url.openStream());
        StringBuilder data = new StringBuilder();

        while (stream.hasNext()) {
            data.append(stream.nextLine());
        }

        JsonObject json = new JsonObject();
        try {
            json = (JsonObject) Jsoner.deserialize(data.toString());
        } catch (JsonException e) {
            e.printStackTrace();
        }

        return json;
    }

    public JsonObject getRecipesByDish(String dish) throws Exception
    {
        URL url = new URL(baseUrl+"q="+dish);
        Scanner stream = new Scanner(url.openStream());
        StringBuilder data = new StringBuilder();

        while (stream.hasNext()) {
            data.append(stream.nextLine());
        }

        JsonObject json = new JsonObject();
        try {
            json = (JsonObject) Jsoner.deserialize(data.toString());
        } catch(JsonException e) {
            e.printStackTrace();
        }

        return json;
    }


    public String formatRecipeAsString(JsonObject doc){
        String results = "";
        JsonArray recipesArray = (JsonArray) doc.get("results");

        for (int i = 0; i < recipesArray.size(); i++) {
            JsonObject recipe = (JsonObject) recipesArray.get(i);
            results += recipe.get("title") + "\n";
            results += recipe.get("href") + "\n";
            results += recipe.get("ingredients") + "\n";
        }

        return results;
    }

    public void saveRecipes(String text, String outfile) {

        try (DataOutputStream fout = new DataOutputStream(new FileOutputStream(outfile))) {
            fout.write(text.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
