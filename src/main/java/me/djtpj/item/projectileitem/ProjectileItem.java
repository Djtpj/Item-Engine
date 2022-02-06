package me.djtpj.item.projectileitem;

import me.djtpj.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

public abstract class ProjectileItem extends Item {

    protected boolean projectileLand = true;

    public ProjectileItem(String name, Material itemTemplate) {
        super(name, itemTemplate);


    }

// TODO onProjectileThrow

    @EventHandler
    public void onProjectileLand(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Projectile)){
            return;
        }

        Projectile projectile = event.getEntity();

        if (!(projectile.getShooter() instanceof Player)) {
            return;
        }

        Player player = (Player) projectile.getShooter();


        if (projectileLand) {


        if (this.isSimilar(player.getInventory().getItemInMainHand())){
            projectileLandAction(event);
        }
        }

    }

    protected void projectileLandAction(ProjectileHitEvent event){
        Bukkit.broadcastMessage(ChatColor.RED + "This is the template item");
    }

    // TODO PROJECTILE LAUNCH EVENT
}
