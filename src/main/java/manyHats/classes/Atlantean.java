package manyHats.classes;

import manyHats.model.ManyHatsPlayer;
import manyHats.util.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

public class Atlantean implements Listener {

  private final PlayerManager playerManager;

  public Atlantean(PlayerManager playerManager) {
    this.playerManager = playerManager;
  }

  /** When an Atlantean player joins, apply the base Atlantean passive effect to them */
  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    Optional<ManyHatsPlayer> playerOptional = playerManager.getPlayer(e.getPlayer().getName());
    playerOptional.ifPresent(Atlantean::applyEffect);
  }

  /** Public b/c accessed from the main command driver */
  public static void applyEffect(ManyHatsPlayer p) {
    if (p.getPlayerClassMeta().getPlayerClass() == ManyHatsPlayer.PlayerClass.ATLANTEAN) {
      p.getPlayer()
          .addPotionEffect(
              new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 1));
      p.getPlayer()
          .addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
    }
  }
}
