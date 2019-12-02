package com.tweska.sweetheads.inventorygui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SearchResultGUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof SearchResultGUI)) {
            return;
        }

        if(event.getRawSlot() > 53) {
            return;
        }

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        SearchResultGUI gui = (SearchResultGUI) event.getInventory().getHolder();
        Inventory inventory = event.getInventory();
        int rawSlot = event.getRawSlot();
        Player player = (Player) event.getWhoClicked();

        if (rawSlot == 45) {
            gui.openPrevious(player, inventory);
            return;
        } else if (rawSlot == 53) {
            gui.openNext(player, inventory);
            return;
        }

        player.setItemOnCursor(inventory.getItem(rawSlot));
    }
}
