package manyHats.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.entity.Player;

// TODO Make XP stuff better
@Setter
@Getter
@AllArgsConstructor
@ToString
public class ManyHatsPlayer {

  private transient Player player;

  @Expose private PlayerClassMeta playerClassMeta;

  public enum PlayerClass {
    GOURMET,
    ATLANTEAN,
    SPELUNKER,
    NONE
  }

  @Getter
  @AllArgsConstructor
  @ToString
  public static class PlayerClassMeta {
    @Expose private final PlayerClass playerClass;
    @Expose private final int classLevel;
    @Expose private final int xpToNextLevel;

    public PlayerClassMeta(PlayerClass playerClass) {
      this.playerClass = playerClass;
      classLevel = 1;
      xpToNextLevel = 1000;
    }
  }

  public boolean hasClass() {
    if (playerClassMeta == null) {
      return false;
    }
    return playerClassMeta.getPlayerClass() != PlayerClass.NONE;
  }

  public ManyHatsPlayer(Player player) {
    this.player = player;
    this.playerClassMeta = new PlayerClassMeta(PlayerClass.NONE);
  }

  public ManyHatsPlayer(Player player, PlayerClass playerClass) {
    this.player = player;
    this.playerClassMeta = new PlayerClassMeta(playerClass);
  }
}
