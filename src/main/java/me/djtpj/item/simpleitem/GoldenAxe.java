package me.djtpj.item.simpleitem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

public class GoldenAxe extends SimpleItem {

    public GoldenAxe() {
        super(ChatColor.GOLD + "Golden Axe", Material.GOLDEN_AXE);

        // Add all the different lore
        addLore("");
        addLore("This is a test");
        addLore(ChatColor.BLUE + "This is a colored test");

        // Set the item to be unbreakable
        this.setUnbreakable(true);

        // Add itemFlags
        this.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        this.addItemFlag(ItemFlag.HIDE_UNBREAKABLE);

        // Update all the item meta from all the changes that were made
        this.item.setItemMeta(meta);

        this.setDoRightClick(true);

    }
}
