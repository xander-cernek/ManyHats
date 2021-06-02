package manyHats.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import manyHats.model.ManyHatsPlayer;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Log
public class Dao {

  private final ObjectMapper objectMapper;

  public Dao() {
    objectMapper =
        new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
  }

  public void saveMap(Map<String, ManyHatsPlayer> map) {
    try {
      objectMapper.writeValue(new File("src/main/resources/players.yaml"), map);
    } catch (IOException e) {
      log.info("Could not open players file");
    }
  }

  public Map<String, ManyHatsPlayer> retrieveAllPlayers() {
    try {
      TypeReference<HashMap<String, ManyHatsPlayer>> typeReference =
          new TypeReference<HashMap<String, ManyHatsPlayer>>() {};
      return objectMapper.readValue(new File("src/main/resources/players.yaml"), typeReference);
    } catch (IOException e) {
      log.info("Could not open players file");
    }
    return Collections.emptyMap();
  }
}
