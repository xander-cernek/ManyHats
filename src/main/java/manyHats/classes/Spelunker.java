package manyHats.classes;

import lombok.extern.java.Log;
import manyHats.model.ManyHatsPlayer;
import manyHats.util.MathUtility;
import manyHats.util.PlayerManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log
public class Spelunker implements Listener {

  private final PlayerManager playerManager;

  public Spelunker(PlayerManager playerManager) {
    this.playerManager = playerManager;
  }

  /**
   * This fires whenever a block is broken. Check the type of block and drop 1-5 XP on the block's
   * broken location (simulating a normal XP drop) if it's an ore.
   */
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Block block = event.getBlock();
    Optional<ManyHatsPlayer> playerClassOptional =
        playerManager.getPlayer(event.getPlayer().getName());
    if (playerClassOptional.isPresent()
        && playerClassOptional.get().getPlayerClassMeta().getPlayerClass()
            == ManyHatsPlayer.PlayerClass.SPELUNKER) {
      if (isOre(block.getType())) {
        ((ExperienceOrb) block.getWorld().spawn(block.getLocation(), ExperienceOrb.class))
            .setExperience(MathUtility.getRandomNumber(1, 6));
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
