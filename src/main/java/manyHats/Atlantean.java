package manyHats;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

public class Atlantean implements Listener {

  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    Optional<ClassPlayer> playerOptional = PlayerManager.getPlayer(e.getPlayer().getName());
    playerOptional.ifPresent(Atlantean::applyEffect);
  }

  public static void applyEffect(ClassPlayer p) {
    if (p.getPlayerClass() == ClassPlayer.PlayerClass.ATLANTEAN) {
      p.getPlayer()
          .addPotionEffect(
              new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 1));
      p.getPlayer()
          .addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
    }
  }
}
