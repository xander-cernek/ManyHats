import manyHats.util.PlayerManager;
import org.junit.jupiter.api.BeforeAll;

public class Test {

  static PlayerManager playerManager;

  @BeforeAll
  static void setup() {
    playerManager = new PlayerManager();
  }

  @org.junit.jupiter.api.Test
  public void foo() {
    playerManager.loadInitialPlayers();
  }
}
