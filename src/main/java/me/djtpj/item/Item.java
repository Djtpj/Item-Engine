package me.djtpj.item;

import me.djtpj.utils.Manager;
import org.apache.commons.text.CaseUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class Item implements Listener {

    /* This is a comment written out of despair
    I am being written months after coming back to this project
    This code has not been touched in forever.
    Not nearly enough code documentation was completed.
    The code is going to have to be slowly and tediously worked through in an attempt to properly document this code

    Do not make the same mistake that lead to my existance
     */

    // Normal Item data
    protected String name;
    // Lore is just a small description of the item. But you can put custom messages there instead
    protected ArrayList<String> lore;

    // Item variables
    protected ItemStack item;
    protected ItemMeta meta;

    protected String id;

    // This is the item icon
    protected Material itemTemplate;

    // Is a boolean so I can tell if the item has durability or not
    protected boolean isUnbreakable;

    protected boolean hasLore = false;

    // Event variables. When events are triggered, it checks if the event is enabled, and then runs the event code.
    boolean doRightClick = false;
    boolean doLeftClick = false;
    boolean isProtective = false;
    boolean doShiftRightClick = false;
    boolean doShiftLeftClick = false;

    // The default lore color
    ChatColor defaultLoreColor;

    // Constructor
    public Item(String name, Material itemTemplate) {

        // changes the name to be a camel case version of itself, and sets the id to that version of the item name
        id = CaseUtils.toCamelCase(ChatColor.stripColor(name), false);

        // Update and instantiate all the variables
        this.item = new ItemStack(itemTemplate);
        this.meta = this.item.getItemMeta();
        this.lore = new ArrayList<>();
        setName(name);

        activateThisItem();
    }

    // Registers the item
    protected void activateThisItem() {

        Manager.itemManager.registerItem(this);

    }

    // EventListeners
    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        // Sets the player variable
        Player player = event.getPlayer();
        // If this item is the same item as the one in the players main hand
        if (this.isSimilar(player.getInventory().getItemInMainHand())) {
            // If the player is sneaking run the sneaking actions
            if (player.isSneaking()){
                switch (event.getAction()){
                    case LEFT_CLICK_AIR:
                    case LEFT_CLICK_BLOCK:
                        if (doShiftLeftClick) onShiftLeftClick(event);
                        break;
                    case RIGHT_CLICK_AIR:
                    case RIGHT_CLICK_BLOCK:
                        if (doShiftRightClick) onShiftRightClick(event);
                        break;
                }
            }

            // If the player is NOT sneaking, run the not sneaking action
            else if (!player.isSneaking()){
                switch (event.getAction()) {
                    case LEFT_CLICK_AIR:
                    case LEFT_CLICK_BLOCK:
                        if (doLeftClick) onLeftClick(event);
                        break;
                    case RIGHT_CLICK_AIR:
                    case RIGHT_CLICK_BLOCK:
                        if (doRightClick) onRightClick(event);
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onDamageTakenEvent(EntityDamageByEntityEvent event) {
        // If the entity taking damage is not a player, don't run the rest of code
    if (!(event.getEntity() instanceof Player)){
        return;
    }

    // Set the player variable
    Player player = (Player) event.getEntity();

    // Gets and sets the damager
    // (This can be of any entity type
    Entity damager = event.getDamager();

    // If this item is a 'protective item'
        if (isProtective) {
            // If the held item is the same as this item
            if (this.isSimilar(player.getInventory().getItemInMainHand())){
                // Run the damage Taken action
                onDamageTaken(event);
            }
        }

    }

    // Each one runs a template item function, I @Override each of them in the children classes if I want to give them something to do
    protected void onRightClick(PlayerInteractEvent event) {
        templateItemAction(event);
    }

    protected void onLeftClick(PlayerInteractEvent event) {
        templateItemAction(event);
    }

    protected void onShiftRightClick(PlayerInteractEvent event) {
        templateItemAction(event);
    }

    protected void onShiftLeftClick(PlayerInteractEvent event) {
        templateItemAction(event);
    }

    protected void onDamageTaken(EntityDamageByEntityEvent event) {
        templateItemAction(event);
    }

    // This is is to tell when the code isn't going through
    public static void templateItemAction(Event event) {
        Bukkit.broadcastMessage(ChatColor.RED + "This is the template item for the " + event.getEventName());
    }

    // Getters
    public ItemStack getItem() {
        return item;
    }

    public String getID() {
        return id;
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    public ItemMeta getMeta() {
        return meta;
    }

    public boolean isUnbreakable() {
        return isUnbreakable;
    }

    public boolean hasLore() {
        return hasLore;
    }

    // Setters

    private void setName(String name) {
        this.name = name;
        this.meta.setDisplayName(name);
        this.item.setItemMeta(meta);
    }

    // Sets whether or not the item is unbreakable
    protected void setUnbreakable(Boolean bool) {
        isUnbreakable = bool;
        this.meta.setUnbreakable(bool);
        this.item.setItemMeta(meta);
    }

    // Set the enchantments
    protected void addEnchantment(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        this.item.setItemMeta(meta);
    }

    protected void addLore(String addedLore) {

        if (defaultLoreColor != null){
            this.lore.add(defaultLoreColor + addedLore);
        }
        else{
            this.lore.add(addedLore);
        }

        this.meta.setLore(lore);
        this.item.setItemMeta(meta);
        this.hasLore = true;
    }

    protected void addLore(String addedLore, Boolean overrideColor){

        if (defaultLoreColor != null && overrideColor) {
            this.lore.add(defaultLoreColor + addedLore);
        }
        else {
            this.lore.add(addedLore);
        }

        this.meta.setLore(lore);
        this.item.setItemMeta(meta);

    }

    // Item flags have some extra abilities to add to items
    protected void addItemFlag(ItemFlag itemFlag) {
        this.meta.addItemFlags(itemFlag);
        this.item.setItemMeta(meta);
    }

    protected void setDoRightClick(boolean doRightClick) {
        this.doRightClick = doRightClick;
    }

    protected void setLeftClickEvent(boolean leftClickEvent) {
        this.doLeftClick = leftClickEvent;
    }

    protected void setProtective(boolean damageTakenWhileHolding) {
        this.isProtective = damageTakenWhileHolding;
    }

    protected void setDoShiftRightClick(boolean bool) {

        this.doShiftRightClick = bool;

    }

    protected void setDoShiftLeftClick(boolean bool) {
        this.doShiftLeftClick = bool;
    }

    public String getName() {
        return this.name;
    }

    protected void setDefaultLoreColor(ChatColor chatColor) {
        this.defaultLoreColor = chatColor;
    }

    public boolean isSimilar(ItemStack similarItem) {
        if (similarItem.getItemMeta() == null && meta == null) {
            Manager.plugin.getLogger().log(Level.SEVERE, "ERROR OCCURED WHILE USING 'isSimilar', both meta values are null");
            return false;
        }

        if (similarItem.getItemMeta() == null) {
            Manager.plugin.getLogger().log(Level.SEVERE, "ERROR OCCURED WHILE USING 'isSimilar', SimilarItemMeta is null");
            return false;
        }

        if (meta == null) {
            Manager.plugin.getLogger().log(Level.SEVERE, "ERROR OCCURED WHILE USING 'isSimilar', ItemMeta is null");
        }
        ItemMeta similarItemMeta = similarItem.getItemMeta();
        List<String> similarItemLore = similarItemMeta.getLore();



        if (!similarItem.hasItemMeta() == this.item.hasItemMeta()) return false;

        else if (!similarItemMeta.hasLore() == this.item.getItemMeta().hasLore()) return false;

        else if (!similarItemMeta.hasEnchants() == meta.hasEnchants()) return false;

        else if (!(similarItem.getType() == item.getType())) return false;

        else if (!(similarItem.getAmount() == item.getAmount())) return false;

        else if (!similarItem.getEnchantments().equals(item.getEnchantments())) return false;

        else if (!(similarItemMeta.getDisplayName().equals(meta.getDisplayName()))) return false;

        else if (!(similarItemMeta.getItemFlags().equals(meta.getItemFlags()))) return false;

        else if (!(similarItemMeta.isUnbreakable() == this.meta.isUnbreakable()) || !(similarItemMeta.getLocalizedName().equals(meta.getLocalizedName())) || !(similarItemMeta.hasLocalizedName() == meta.hasLocalizedName()))
            return false;

        else return true;
    }
}
