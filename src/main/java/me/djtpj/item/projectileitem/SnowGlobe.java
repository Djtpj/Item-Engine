package me.djtpj.item.projectileitem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowman;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SnowGlobe extends ProjectileItem{
    public SnowGlobe() {
        super(ChatColor.AQUA + "Snow-globe", Material.SNOWBALL);

        addLore("");
        addLore("Throw to snowballgolem :D");
        addLore("");

        addEnchantment(Enchantment.FROST_WALKER, 0);

        item.setItemMeta(meta);

    }

    @Override
    protected void projectileLandAction(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        Player player = (Player) projectile.getShooter();

        Snowman snowman = projectile.getWorld().spawn(projectile.getLocation(), Snowman.class);

        snowman.setCustomName(ChatColor.AQUA + "Snowboi");
        snowman.setCustomNameVisible(true);

        snowman.getWorld().spawnParticle(Particle.SNOWFLAKE, snowman.getLocation(), 6);

    }



}
