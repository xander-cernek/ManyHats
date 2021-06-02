package manyHats.util;

import lombok.extern.java.Log;
import manyHats.model.ManyHatsPlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;

@Log
public class PlayerManager {

  private final Map<String, ManyHatsPlayer> playerMap;
  private final Dao dao;

  public PlayerManager(Dao dao) {
    this.dao = dao;
    playerMap = dao.retrieveAllPlayers();
  }

  public void addPlayer(ManyHatsPlayer player) {
    playerMap.putIfAbsent(player.getPlayer().getName(), player);
    dao.saveMap(playerMap);
  }

  public void addPlayer(Player player) {
    try {
      ManyHatsPlayer savePlayer = new ManyHatsPlayer(player);
      playerMap.putIfAbsent(player.getName(), savePlayer);
      dao.saveMap(playerMap);
    } catch (NullPointerException e) {
      log.info(String.format("Player %s does not exist...?", player));
    }
  }

  public Optional<ManyHatsPlayer> getPlayer(String playerName) {
    return Optional.of(playerMap.get(playerName));
  }

  public void forceSave() {
    dao.saveMap(playerMap);
  }
}
