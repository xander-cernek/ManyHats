package manyHats;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ManyHats extends JavaPlugin {

  @Override
  public void onEnable() {
    getLogger().info("Apple loaded. Ligma balls Ben");
    getServer().getPluginManager().registerEvents(new MyListener(), this);
    getServer().getPluginManager().registerEvents(new FoodRanger(), this);
    getServer().getPluginManager().registerEvents(new Atlantean(), this);
    getServer().getPluginManager().registerEvents(new Spelunker(), this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName()
        .equalsIgnoreCase(
            "setClass")) { // If the player typed /basic then do the following, note: If you only
      // registered this executor for one command, you don't need this
      // doSomething

      if (sender instanceof Player) {
        Player p = (Player) sender;
        if (label != null) {
          switch (label.toLowerCase()) {
            case "ranger":
              sender.sendMessage(
                  ChatColor.DARK_GREEN + "Welcome to the mysterious Culinary Rangers");
              PlayerManager.addPlayer(new ClassPlayer(p, ClassPlayer.PlayerClass.RANGER));
              break;
            case "atlantean":
              sender.sendMessage(ChatColor.BLUE + "Well met, Atlantean Heir");
              ClassPlayer p2 = new ClassPlayer(p, ClassPlayer.PlayerClass.ATLANTEAN);
              PlayerManager.addPlayer(p2);
              Atlantean.applyEffect(p2);
              break;
            case "spelunker":
              sender.sendMessage(ChatColor.GOLD + "Ho ho! Greetings, fellow Spelunker!");
              PlayerManager.addPlayer(new ClassPlayer(p, ClassPlayer.PlayerClass.SPELUNKER));
              break;
            default:
              sender.sendMessage(ChatColor.RED + "Invalid class. Try 'Ranger'' or 'Atlantean'");
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
