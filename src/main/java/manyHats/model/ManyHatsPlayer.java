package manyHats.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

// TODO Make XP stuff better
@Setter
@Getter
public class ManyHatsPlayer {

  @JsonIgnore private final Player player;

  private PlayerClassMeta playerClassMeta;

  public enum PlayerClass {
    GOURMET,
    ATLANTEAN,
    SPELUNKER,
    NONE
  }

  public ManyHatsPlayer(Player player) {
    this.player = player;
    this.playerClassMeta =
        PlayerClassMeta.builder()
            .playerClass(PlayerClass.NONE)
            .classLevel(1)
            .xpToNextLevel(1000)
            .build();
  }

  public ManyHatsPlayer(Player player, PlayerClass playerClass) {
    this.player = player;
    this.playerClassMeta =
        PlayerClassMeta.builder()
            .playerClass(playerClass)
            .classLevel(1)
            .xpToNextLevel(1000)
            .build();
  }
}
