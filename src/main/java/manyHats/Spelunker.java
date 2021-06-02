package manyHats;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Spelunker implements Listener {

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Block block = event.getBlock();
    Optional<ClassPlayer> playerClassOptional =
        PlayerManager.getPlayer(event.getPlayer().getName());
    if (playerClassOptional.isPresent()
        && playerClassOptional.get().getPlayerClass() == ClassPlayer.PlayerClass.SPELUNKER) {
      if (isOre(block.getType())) {
        ((ExperienceOrb) block.getWorld().spawn(block.getLocation(), ExperienceOrb.class))
            .setExperience(5);
      }
    }
  }

  private boolean isOre(Material material) {
    List<Material> ores =
        Arrays.asList(
            Material.COAL_ORE,
            Material.IRON_ORE,
            Material.GOLD_ORE,
            Material.DIAMOND_ORE,
            Material.EMERALD_ORE,
            Material.LAPIS_ORE,
            Material.REDSTONE_ORE,
            Material.NETHER_GOLD_ORE,
            Material.NETHER_QUARTZ_ORE);
    return ores.contains(material);
  }
}
