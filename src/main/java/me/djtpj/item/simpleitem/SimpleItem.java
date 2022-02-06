package me.djtpj.item.simpleitem;

import me.djtpj.item.Item;
import org.bukkit.Material;

public abstract class SimpleItem extends Item {

    // This is because I have some stuff that will eventually go here, I just needed a buffer between the classes. I'm gonna have a couple more of these types of items
    public SimpleItem(String name, Material itemTemplate) {
    super(name, itemTemplate);

    }
}
