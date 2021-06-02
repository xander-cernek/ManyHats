package manyHats;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerManager {

  private static final Map<String, ClassPlayer> playerMap = new HashMap<>();

  public static void addPlayer(ClassPlayer player) {
    playerMap.put(player.getPlayer().getName(), player);
  }

  public static Optional<ClassPlayer> getPlayer(String playerName) {
    return Optional.of(playerMap.get(playerName));
  }
}
