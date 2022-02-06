package me.djtpj.item.armoritem;

import org.apache.commons.text.CaseUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

import static me.djtpj.utils.Manager.itemManager;

public abstract class ArmorSet {

    String armorSetName;
    ArrayList<ArmorItem> items;

    public ArmorSet(String armorSetName) {
        this.armorSetName = CaseUtils.toCamelCase(armorSetName, false);

        items = new ArrayList<>();
    }

    @EventHandler
    public void onDamageTakenWhileSetEquipped(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

    }

    protected abstract void damageTakenWhileSetEquippedAction(EntityDamageEvent event);

    public void appendNewItem(ArmorItem armorItem) {
        items.add(armorItem);
    }

    public ArrayList<ArmorItem> getArmorItems() {
        return items;
    }

    public String getID() {
        return armorSetName;
    }

    public void register() {

        itemManager.registerArmorSet(this);
    }
}
