package manyHats;

import org.bukkit.entity.Player;

public class ClassPlayer {

  private final Player player;
  private PlayerClass playerClass;

  public enum PlayerClass {
    RANGER,
    ATLANTEAN,
    SPELUNKER,
    NONE
  }

  public ClassPlayer(Player player) {
    this.player = player;
    this.playerClass = PlayerClass.NONE;
  }

  public ClassPlayer(Player player, PlayerClass playerClass) {
    this.player = player;
    this.playerClass = playerClass;
  }

  public Player getPlayer() {
    return player;
  }

  public PlayerClass getPlayerClass() {
    return playerClass;
  }

  public void setPlayerClass(PlayerClass playerClass) {
    this.playerClass = playerClass;
  }
}
