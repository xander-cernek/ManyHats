package manyHats.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import manyHats.model.ManyHatsPlayer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Log
@NoArgsConstructor
public class Dao {

  public void saveMap(Map<String, ManyHatsPlayer> map) {
    try {
      Gson gson =
          new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
      FileWriter writer = new FileWriter("players.json");
      gson.toJson(map, writer);
      writer.flush();
      writer.close();
    } catch (IOException e) {
      log.info(String.format("Could not open players file, %s", e.toString()));
    }
  }

  public Map<String, ManyHatsPlayer> retrieveAllPlayers() {
    try {
      Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      Reader reader = Files.newBufferedReader(Paths.get("players.json"));
      return gson.fromJson(reader, new TypeToken<HashMap<String, ManyHatsPlayer>>() {}.getType());
    } catch (IOException e) {
      log.info(String.format("Could not open players file, %s", e.toString()));
    }
    return Collections.emptyMap();
  }
}
