package me.djtpj.item.fooditem;

import me.djtpj.item.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public abstract class FoodItem extends Item {

    protected Integer hunger;
    protected Boolean doEatEvent;

    public FoodItem(String name, Material itemTemplate) {
        super(name, itemTemplate);
    }


    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        // Gets the player
        Player player = event.getPlayer();

        if (this.isSimilar(event.getItem())) {
            // Since there is no way to add hunger to a player, just set the player's food level, to their level + plus our new one
            // Also check to make sure that you are only add hunger if it isn't null
            if (hunger != null) {
                player.setFoodLevel(player.getFoodLevel() + hunger);
            }
            // If the eat event is allowed, then do the eat event action
            if (doEatEvent)
                onEatAction(event);
        }
    }

    protected void onEatAction(PlayerItemConsumeEvent event) {
        this.templateItemAction(event);
    }

}
