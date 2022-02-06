package me.djtpj.itemframework.cmd;

import me.djtpj.item.Item;
import me.djtpj.utils.ItemManager;
import me.djtpj.utils.Utility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CustomCommand implements CommandExecutor {

//    // This command allows me to easily access each of the custom Items in game
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("custom")){
            if (!(sender instanceof Player)) {
                // if a console is sending this message then bounce the command
                Utility.bounceCommand();
                return true;
            }
            Player player = (Player) sender;
            if (args.length == 0){
                // If the player did not enter an item to give, then tell them the usage of the command.
                player.sendMessage(ChatColor.DARK_RED + "Usage: /custom <custom_item_id>");
                return true;

            }
            else {
                // For each custom item in the constant CustomItems check if the entered ID matches one of the custom item's ID's
                for (Item i : ItemManager.allCustomItems){
                    // If the first argument of the command is equal to the item's itemID
                    if (args[0].equalsIgnoreCase(i.getID())) {
                        HashMap<Integer, ChatColor> colorHashMap = new HashMap<>();
                        colorHashMap.put(0, ChatColor.BLACK);
                        colorHashMap.put(1, ChatColor.DARK_RED);
                        colorHashMap.put(2, ChatColor.RED);
                        colorHashMap.put(3, ChatColor.GOLD);
                        colorHashMap.put(4, ChatColor.YELLOW);
                        colorHashMap.put(5, ChatColor.DARK_GREEN);
                        colorHashMap.put(6, ChatColor.GREEN);
                        colorHashMap.put(7, ChatColor.AQUA);
                        colorHashMap.put(8, ChatColor.DARK_AQUA);
                        colorHashMap.put(9, ChatColor.DARK_BLUE);
                        colorHashMap.put(10, ChatColor.BLUE);
                        colorHashMap.put(11, ChatColor.LIGHT_PURPLE);
                        colorHashMap.put(12, ChatColor.DARK_PURPLE);
                        colorHashMap.put(13, ChatColor.WHITE);
                        colorHashMap.put(14, ChatColor.GRAY);
                        colorHashMap.put(15, ChatColor.DARK_GRAY);
                        colorHashMap.put(16, ChatColor.BLACK);
                        int rand_int1 = new Random().nextInt(16);

                        player.sendMessage(colorHashMap.get(rand_int1) + player.getDisplayName() + "" + ChatColor.RESET + " was given " + i.getName());
                        player.getInventory().addItem(i.getItem());
                    }

                }
            }

        }
        return false;
    }



}
