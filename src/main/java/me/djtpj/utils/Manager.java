package me.djtpj.utils;

import me.djtpj.item.Item;
import me.djtpj.item.armoritem.ArmorSet;
import me.djtpj.item.damageitem.OmegaSword;
import me.djtpj.item.fooditem.Hamburger;
import me.djtpj.item.projectileitem.SnowGlobe;
import me.djtpj.item.simpleitem.FireSword;
import me.djtpj.item.simpleitem.TemplateItem;
import me.djtpj.itemframework.cmd.ArmorSetCommand;
import me.djtpj.itemframework.cmd.ArmorSetTabComplete;
import me.djtpj.itemframework.cmd.CustomCommand;
import me.djtpj.itemframework.cmd.CustomTabComplete;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Manager {

    public static ItemManager itemManager;

    public static JavaPlugin plugin;

    boolean usePremadeItems = false;

    public Manager(JavaPlugin plugin) {

        itemManager = new ItemManager();
        this.plugin = plugin;

        registerPremadeItems();

    }

    public void onEnable() {
        plugin.getCommand("custom").setExecutor(new CustomCommand());
        plugin.getCommand("custom").setTabCompleter(new CustomTabComplete());

        plugin.getCommand("armorset").setExecutor(new ArmorSetCommand());
        plugin.getCommand("armorset").setTabCompleter(new ArmorSetTabComplete());

        registerPremadeItems();
    }

    public void registerNewItem(Item item) {
        ItemManager.allCustomItems.add(item);
        plugin.getServer().getPluginManager().registerEvents(item, plugin);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    private void registerPremadeItems() {
        if (usePremadeItems){
            registerNewItem(new FireSword());
            registerNewItem(new SnowGlobe());
            registerNewItem(new TemplateItem());
            registerNewItem(new OmegaSword());
            registerNewItem(new Hamburger());
        }
    }

    public void setUsePremadeItems(boolean bool) {
        this.usePremadeItems = bool;
    }

}
