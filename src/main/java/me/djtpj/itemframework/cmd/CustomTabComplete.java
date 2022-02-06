package me.djtpj.itemframework.cmd;

import me.djtpj.item.Item;
import me.djtpj.utils.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CustomTabComplete implements TabCompleter {

    List<String> arguments = new ArrayList<String>();


    /* I'm honestly not sure how this code works, I borrowed it from a YT vid.
    It adds a tab completer for the commands, so it makes it a little easier to use in the game.
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (arguments.isEmpty()) {
            for (Item i : ItemManager.allCustomItems) {
                arguments.add(i.getID());
            }
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1){
            for (String a : arguments){
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        return null;
    }
}
