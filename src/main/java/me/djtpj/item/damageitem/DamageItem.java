package me.djtpj.item.damageitem;

import me.djtpj.item.Item;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

public abstract class DamageItem extends Item {

    Integer damage;
    Boolean isAggressive = false;

    public DamageItem(String name, Material itemTemplate) {
        super(name, itemTemplate);

    }

    protected void setDamage(Integer damage) {
        this.damage = damage;
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
    }

    @EventHandler()
    public void onDamageDealtWithItem(EntityDamageByEntityEvent event){

        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();
        if (this.isSimilar(player.getInventory().getItemInMainHand()) && isAggressive){
            onDamageDealtAction(event);
        }
    }

    protected void onDamageDealtAction(EntityDamageByEntityEvent event) {

        templateItemAction(event);

    }
}
