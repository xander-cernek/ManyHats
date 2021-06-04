package manyHats;

import manyHats.classes.Atlantean;
import manyHats.model.ManyHatsPlayer;
import manyHats.util.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class Command implements CommandExecutor {

  private final PlayerManager playerManager;

  public Command(PlayerManager playerManager) {
    this.playerManager = playerManager;
  }

  @Override
  public boolean onCommand(
      CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("setClass")) {
      if (sender instanceof Player) {
        Player p = (Player) sender;
        Optional<ManyHatsPlayer> player = playerManager.getPlayer(p.getName());
        if (player.isPresent()) {
          if (!player.get().hasClass()) {
            if (args[0] != null) {
              switch (args[0].toLowerCase()) {
                case "gourmet":
                  sender.sendMessage(ChatColor.DARK_GREEN + "Welcome to the mysterious Gourmets");
                  playerManager.updatePlayer(
                      new ManyHatsPlayer(p, ManyHatsPlayer.PlayerClass.GOURMET));
                  break;
                case "atlantean":
                  sender.sendMessage(ChatColor.BLUE + "Well met, Atlantean Heir");
                  ManyHatsPlayer p2 = new ManyHatsPlayer(p, ManyHatsPlayer.PlayerClass.ATLANTEAN);
                  playerManager.updatePlayer(p2);
                  Atlantean.applyEffect(p2);
                  break;
                case "spelunker":
                  sender.sendMessage(ChatColor.GOLD + "Ho ho! Greetings, fellow Spelunker!");
                  playerManager.updatePlayer(
                      new ManyHatsPlayer(p, ManyHatsPlayer.PlayerClass.SPELUNKER));
                  break;
                default:
                  sender.sendMessage(
                      ChatColor.RED
                          + String.format(
                              "Invalid class %s. Try 'Gourmet', 'Atlantean', or 'Spelunker'",
                              args[0].toLowerCase()));
                  break;
              }
            }
          } else {
            sender.sendMessage(ChatColor.RED + "You already have a class!");
          }
        } else {
          sender.sendMessage(ChatColor.RED + "You already have a class!");
        }
      }
      playerManager.forceSave();
      return true;
    } // If this has happened the function will return true.
    // If this hasn't happened the value of false will be returned.
    return false;
  }
}
