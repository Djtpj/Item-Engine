package me.djtpj.item.damageitem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;

public class OmegaSword extends DamageItem {
    public OmegaSword() {
        super(ChatColor.DARK_RED + "Omega Sword", Material.NETHERITE_SWORD);

        setDamage(32);

        setUnbreakable(true);

        setDoRightClick(true);

        item.setItemMeta(meta);

    }

    @Override
    protected void onRightClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        player.sendMessage("Help 1");

        ArmorStand armorStand = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
    }
}
