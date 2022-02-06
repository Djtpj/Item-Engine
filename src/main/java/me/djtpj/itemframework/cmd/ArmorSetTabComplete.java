package me.djtpj.itemframework.cmd;

import me.djtpj.item.Item;
import me.djtpj.item.armoritem.ArmorSet;
import me.djtpj.utils.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ArmorSetTabComplete implements TabCompleter {

    List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (arguments.isEmpty()) {
            for (ArmorSet a : ItemManager.allArmorSets) {
                arguments.add(a.getID());
                }

            List<String> result = new ArrayList<>();
            if (args.length == 1) {
                for (String a : arguments) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())){
                        result.add(a);
                    }
                    return result;
                }
            }
        }
        return null;
    }

}
