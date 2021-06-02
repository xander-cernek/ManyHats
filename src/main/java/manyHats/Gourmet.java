package manyHats;

import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Optional;

public class Gourmet implements Listener {

  @EventHandler
  public void onKill(EntityDeathEvent e) {
    try {
      String killer = Objects.requireNonNull(e.getEntity().getKiller()).getName();

      Optional<ClassPlayer> player = PlayerManager.getPlayer(killer);

      if (player.isPresent() && player.get().getPlayerClass() == ClassPlayer.PlayerClass.GOURMET) {
        if (e.getEntity() instanceof Monster) {
          e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), loot());
        }
      }
    } catch (Exception ex) {
      System.out.println(ex.toString());
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
