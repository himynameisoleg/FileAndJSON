import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Task {
  public static void main(String[] args){
      Task t = new Task();
      JsonObject doc = t.readJson("./resources/restaurant-data.json");
      Database db = new Database(doc);
      System.out.println(db.getRestaurant("Hometown BBQ"));
      System.out.println(db.getAvgReviews("Casa Enrique"));
  }

  public JsonObject readJson(String filename) {
      String jsonString = readFile(filename);

      JsonObject obj = new JsonObject();

      try {
          obj = (JsonObject) Jsoner.deserialize(jsonString);
      } catch (JsonException e) {
          e.printStackTrace();
      }

      return obj;
  }

  public String readFile(String filename){
      String content = "";

      try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          String s = reader.readLine();

          while (s != null) {
              content += s;
              s = reader.readLine();
          }
          content = new String(content.getBytes(), StandardCharsets.UTF_8);
      } catch(IOException e) {
          e.printStackTrace();
          System.exit(-1);
      }


      return content;
  }
}