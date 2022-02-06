package me.djtpj.itemframework.cmd;

import me.djtpj.item.armoritem.ArmorItem;
import me.djtpj.item.armoritem.ArmorSet;
import me.djtpj.utils.ItemManager;
import me.djtpj.utils.Manager;
import me.djtpj.utils.Utility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArmorSetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("armorset")) {
            if (!(sender instanceof Player)) {
                Utility.bounceCommand();
                return true;
            }
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage(ChatColor.DARK_RED + "Usage: /armorset <armorset_name>");
                return true;
            }

            else {
                for (ArmorSet a : ItemManager.allArmorSets) {
                    if (args[0].equalsIgnoreCase(a.getID())) {
                        for (ArmorItem i : a.getArmorItems()) {
                            player.getInventory().addItem(i.getItem());
                        }
                    }
                }
            }
        }
        return false;
    }
}
