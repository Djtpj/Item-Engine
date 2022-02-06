package me.djtpj.item.simpleitem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

public class TemplateItem extends SimpleItem {
    public TemplateItem() {
        // This makes sure that an itemID is entered, if this is not entered then most of the listener/event code will not work.
        super(ChatColor.BLUE + "Template Item", Material.DIAMOND);

        // Adding an enchant with level of zero so that the item gets a shiny appearance
        addEnchantment(Enchantment.LOOT_BONUS_MOBS, 0);

        // Setting it to unbreakable
        setUnbreakable(true);

        // Add lore
        addLore("");
        addLore("This is a template item");
        addLore("");

        // Add some item flags to hide a few things
        addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        addItemFlag(ItemFlag.HIDE_UNBREAKABLE);
        addItemFlag(ItemFlag.HIDE_ENCHANTS);

        // This updates the metadata of the item so that all of those changes take effect
        this.item.setItemMeta(this.meta);

        // Because this is the template item, I made it so that all the events that I have programmed in so far are turned on
        setDoRightClick(true);
        setLeftClickEvent(true);
        setDoShiftLeftClick(true);
        setDoShiftRightClick(true);
        setProtective(true);

        // This just adds it to the registry as well as a few other things
//        activateThisItem();
    }


}
