package manyHats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PlayerClassMeta {

  private ManyHatsPlayer.PlayerClass playerClass;
  private int classLevel;
  private int xpToNextLevel;
}
