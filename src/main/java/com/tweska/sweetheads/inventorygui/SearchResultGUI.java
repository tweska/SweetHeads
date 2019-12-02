package com.tweska.sweetheads.inventorygui;

import com.tweska.sweetheads.heads.CustomHead;
import com.tweska.sweetheads.heads.Head;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class SearchResultGUI implements InventoryHolder {
    private List<Inventory> inventories;

    private static final ItemStack prevPage = new CustomHead("Previous page", "2fad8146-186b-4c9c-8c62-7d5ccb083faa", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmIwZjZlOGFmNDZhYzZmYWY4ODkxNDE5MWFiNjZmMjYxZDY3MjZhNzk5OWM2MzdjZjJlNDE1OWZlMWZjNDc3In19fQ==");
    private static final ItemStack nextPage = new CustomHead("Next page", "925b071a-7c83-43e7-9d83-8f231c8217d4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJmM2EyZGZjZTBjM2RhYjdlZTEwZGIzODVlNTIyOWYxYTM5NTM0YThiYTI2NDYxNzhlMzdjNGZhOTNiIn19fQ==");


    public SearchResultGUI(String searchTerm, List<Head> heads) {
        int pages = (int) Math.ceil(heads.size() / 45.0);

        inventories = new LinkedList<>();

        for (int page = 0; page < pages; page++) {
            String title = String.format("Results for \"%s\" (%d/%d)", searchTerm, page+1, pages);
            inventories.add(Bukkit.createInventory(this, 54, title));

            int pageSlots = Math.min(heads.size() - page * 45 , 45);
            for (int slot = 0; slot < pageSlots; slot++) {
                Head head = heads.get(page * 45 + slot);
                ItemStack item = head.getItem();
                inventories.get(page).setItem(slot, item);
            }

            if (page != 0) {
                inventories.get(page).setItem(45, prevPage);
            }
            if (page != pages-1) {
                inventories.get(page).setItem(53, nextPage);
            }
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventories.get(0);
    }

    public void openGUI(Player player) {
        player.openInventory(inventories.get(0));
    }

    public void openNext(Player player, Inventory current) {
        int index = inventories.indexOf(current);

        if (inventories.size() <= ++index) {
            return;
        }
        player.openInventory(inventories.get(index));
    }

    public void openPrevious(Player player, Inventory current) {
        int index = inventories.indexOf(current);

        if (0 > --index) {
            return;
        }
        player.openInventory(inventories.get(index));
    }
}
