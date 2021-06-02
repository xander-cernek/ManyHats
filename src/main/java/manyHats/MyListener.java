package manyHats;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MyListener implements Listener {

  @EventHandler
  public void onLeafBreak(BlockBreakEvent event) {
    Block block = event.getBlock();
    if (block.getType() == Material.OAK_LEAVES) {
      event
          .getBlock()
          .getWorld()
          .dropItem(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
    }
  }
}
