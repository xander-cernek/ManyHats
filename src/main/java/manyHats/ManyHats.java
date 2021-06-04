package manyHats;

import manyHats.classes.Atlantean;
import manyHats.classes.Gourmet;
import manyHats.classes.Spelunker;
import manyHats.util.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ManyHats extends JavaPlugin {

  private final PlayerManager playerManager;

  public ManyHats() {
    this.playerManager = new PlayerManager();
  }

  /**
   * Make sure when you add a new event handler, register the class to this. Otherwise the event
   * handler won't work
   */
  @Override
  public void onEnable() {

    getLogger().info("ManyHats plugin loaded successfully. Ligma balls, Ben!");
    getServer().getPluginManager().registerEvents(new Gourmet(playerManager), this);
    getServer().getPluginManager().registerEvents(new Atlantean(playerManager), this);
    getServer().getPluginManager().registerEvents(new Spelunker(playerManager), this);
    this.getCommand("setClass").setExecutor(new Command(playerManager));

    playerManager.loadInitialPlayers();

    for (Player p : Bukkit.getOnlinePlayers()) {
      playerManager.addPlayer(p);
    }

    playerManager.forceSave();
  }

  @Override
  public void onDisable() {
    playerManager.forceSave();
  }
}
