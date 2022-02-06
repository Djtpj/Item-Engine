package me.djtpj.item.armoritem;

import me.djtpj.item.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public abstract class ArmorItem extends Item {

    ArmorType armorType;
    protected Boolean doDamageTakenWhileEquippedEvent;

    public ArmorItem(String name, Material itemTemplate, ArmorType armorType, Boolean hasArmorSet) {
        super(name, itemTemplate);

        this.armorType = armorType;

        doDamageTakenWhileEquippedEvent = !hasArmorSet;
    }

    @EventHandler
    public void onDamageTakenWhileWearingArmorPiece(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player player = (Player) event.getEntity();
        if (doDamageTakenWhileEquippedEvent) {

            switch (armorType){
                case HELMET:
                    ItemStack armorPiece = player.getEquipment().getHelmet();

                    if (!(armorPiece == null)){
                        if (this.isSimilar(armorPiece)){
                            damageTakenWhileEquippedAction(event);
                        }
                    }
                    break;
                case CHESTPLATE:
                    armorPiece = player.getEquipment().getChestplate();

                    if (!(armorPiece.getItemMeta() == null)) {
                        if (this.isSimilar(armorPiece)) {
                            damageTakenWhileEquippedAction(event);
                        }
                    }
                    break;
                case LEGGINGS:
                    armorPiece = player.getEquipment().getLeggings();

                    if (!(armorPiece == null)) {
                        if (this.isSimilar(armorPiece)) {
                            damageTakenWhileEquippedAction(event);
                        }
                    }
                    break;
                case BOOTS:
                    armorPiece = player.getEquipment().getBoots();

                    if (!(armorPiece == null)) {
                        if (this.isSimilar(armorPiece)) {
                            damageTakenWhileEquippedAction(event);
                        }
                    }
                    break;
            }

        }


    }

    protected void damageTakenWhileEquippedAction(EntityDamageEvent event) {
        templateItemAction(event);
    }
}
