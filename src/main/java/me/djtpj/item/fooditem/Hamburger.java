package me.djtpj.item.fooditem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class Hamburger extends FoodItem{
    public Hamburger() {
        super(ChatColor.GOLD + "Hamburger", Material.COOKED_BEEF);

        addLore("");
        addLore(ChatColor.GRAY + "Hambugou");
        addLore("");

        addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 0);

        this.item.setItemMeta(meta);

        hunger = 1800;

    }
}
