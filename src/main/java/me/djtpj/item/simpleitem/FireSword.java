package me.djtpj.item.simpleitem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;

public class FireSword extends SimpleItem {
    public FireSword() {
        super(ChatColor.GOLD + "Fire Sword", Material.GOLDEN_SWORD);

        addLore("");
        addLore("This sword is fire.");

        this.setUnbreakable(true);

        this.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);
        this.addItemFlag(ItemFlag.HIDE_UNBREAKABLE);

        this.addEnchantment(Enchantment.FIRE_ASPECT, 5);

        this.item.setItemMeta(meta);
        
        setDoShiftRightClick(true);
        
    }

    @Override
    protected void onShiftRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        player.spawnParticle(Particle.FLAME, player.getLocation(), 6);

        Fireball fireball = player.getWorld().spawn(player.getLocation().add(0, 1, 0), Fireball.class);

        fireball.setDirection(player.getLocation().getDirection());
        fireball.setBounce(false);
        fireball.setIsIncendiary(true);
        fireball.setYield(3.0f);
        fireball.setFireTicks(40);
    }
}
