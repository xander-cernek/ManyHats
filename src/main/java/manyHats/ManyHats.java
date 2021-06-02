package manyHats;

import manyHats.classes.Atlantean;
import manyHats.classes.Gourmet;
import manyHats.classes.Spelunker;
import manyHats.model.ManyHatsPlayer;
import manyHats.util.Dao;
import manyHats.util.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ManyHats extends JavaPlugin {

  private final PlayerManager playerManager;

  public ManyHats() {
    this.playerManager = new PlayerManager(new Dao());
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

    for (Player p : Bukkit.getOnlinePlayers()) {
      playerManager.addPlayer(p);
    }
  }

  @Override
  public void onDisable() {
    playerManager.forceSave();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("setClass")) {
      if (sender instanceof Player) {
        Player p = (Player) sender;
        if (label != null) {
          switch (label.toLowerCase()) {
            case "gourmet":
              sender.sendMessage(ChatColor.DARK_GREEN + "Welcome to the mysterious Gourmets");
              playerManager.addPlayer(new ManyHatsPlayer(p, ManyHatsPlayer.PlayerClass.GOURMET));
              break;
            case "atlantean":
              sender.sendMessage(ChatColor.BLUE + "Well met, Atlantean Heir");
              ManyHatsPlayer p2 = new ManyHatsPlayer(p, ManyHatsPlayer.PlayerClass.ATLANTEAN);
              playerManager.addPlayer(p2);
              Atlantean.applyEffect(p2);
              break;
            case "spelunker":
              sender.sendMessage(ChatColor.GOLD + "Ho ho! Greetings, fellow Spelunker!");
              playerManager.addPlayer(new ManyHatsPlayer(p, ManyHatsPlayer.PlayerClass.SPELUNKER));
              break;
            default:
              sender.sendMessage(
                  ChatColor.RED + "Invalid class. Try 'Gourmet', 'Atlantean', or 'Spelunker'");
              break;
          }
        }
      }

      return true;
    } // If this has happened the function will return true.
    // If this hasn't happened the value of false will be returned.
    return false;
  }
}
