package manyHats.classes;

import lombok.extern.java.Log;
import manyHats.model.ManyHatsPlayer;
import manyHats.util.MathUtility;
import manyHats.util.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

@Log
public class Gourmet implements Listener {

  private final PlayerManager playerManager;

  public Gourmet(PlayerManager playerManager) {
    this.playerManager = playerManager;
  }

  /**
   * This fires whenever an entity dies. Check the killer to see if it's a player and the entity to
   * see if it's a monster. If everything checks out, do a simple loot table calculation to drop a
   * random cooked fish with a drop rate of 50%
   */
  @EventHandler
  public void onKill(EntityDeathEvent e) {

    if (e.getEntity().getKiller() != null) {
      String killer = e.getEntity().getKiller().getName();
      Optional<ManyHatsPlayer> player = playerManager.getPlayer(killer);

      if (player.isPresent()
          && player.get().getPlayerClassMeta().getPlayerClass()
              == ManyHatsPlayer.PlayerClass.GOURMET) {
        if (e.getEntity() instanceof Monster) {
          e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), loot());
        }
      }
    }
  }

  private ItemStack loot() {
    int number = MathUtility.getRandomNumber(0, 1);
    if (number == 0) {
      return new ItemStack(Material.COOKED_SALMON, MathUtility.getRandomNumber(0, 1));
    } else {
      return new ItemStack(Material.COOKED_COD, MathUtility.getRandomNumber(0, 1));
    }
  }
}
