package me.djtpj.utils;

import me.djtpj.item.Item;
import me.djtpj.item.armoritem.ArmorSet;
import org.bukkit.event.Listener;

import java.util.ArrayList;


public class ItemManager {
    // A static registry of all the items
    public static ArrayList<Item> allCustomItems;
    public static ArrayList<Listener> allListeners;
    public static ArrayList<Item> allActiveItems;
    public static ArrayList<ArmorSet> allArmorSets;

    ItemManager() {
        // Instantiate the registries
        allCustomItems = new ArrayList<>();
        allListeners = new ArrayList<>();
        allActiveItems = new ArrayList<>();
        allArmorSets = new ArrayList<>();

    }

    // A function to register items externally
    public void registerItem(Item item) {

        allActiveItems.add(item);
        if (!(allListeners.contains(item)))
            allListeners.add(item);

    }

    public void registerArmorSet(ArmorSet armorSet) {
        allArmorSets.add(armorSet);
    }
}
