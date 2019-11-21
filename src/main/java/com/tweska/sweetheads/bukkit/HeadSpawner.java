package com.tweska.sweetheads.bukkit;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.tweska.sweetheads.heads.Head;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class HeadSpawner {

    public static ItemStack getItemStack(Head head) {
        return getItemStack(head, 1);
    }

    public static ItemStack getItemStack(Head head, int quantity) {
        /* Get a new head item stack. */
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, quantity);

        /* Get the metadata of the head. */
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        /* Get a new dummy game profile. */
        GameProfile profile = new GameProfile(head.getUUID(), null);

        /* Set the display name of the head. */
        meta.setDisplayName(head.getName());

        /* Add an extra line of text. */
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Custom Head");
        meta.setLore(lore);

        /* Add the texture to the dummy game profile. */
        profile.getProperties().put("textures", new Property("textures", head.getValue()));

        /* Attempt to set the profile field of the metadata. */
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException|IllegalAccessException exception) {
            exception.printStackTrace();
        }

        /* Update the meta data of the head. */
        item.setItemMeta(meta);

        return item;
    }

    public static List<ItemStack> getItemStacks(List<Head> heads) {
        return getItemStacks(heads, 1);
    }

    public static List<ItemStack> getItemStacks(List<Head> heads, int quantity) {
        /* Create a new list to store the item stacks. */
        List<ItemStack> items = new ArrayList<>();

        /* Get the item stack of each head and add it to the list. */
        for (Head head : heads) {
            ItemStack item = getItemStack(head, quantity);
            items.add(item);
        }

        return items;
    }

    public static void giveHead(Head head, Player player, int quantity) {
        /* Get the inventory and the desired item. */
        Inventory inventory = player.getInventory();
        ItemStack item = getItemStack(head, quantity);

        /* Add the item to the inventory. */
        inventory.addItem(item);
    }

    public static void giveHead(Head head, Player player) {
        giveHead(head, player, 1);
    }

    public static void giveHeads(List<Head> heads, Player player, int quantity) {
        /* Get the inventory and the desired items. */
        Inventory inventory = player.getInventory();
        List<ItemStack> items = getItemStacks(heads, quantity);

        /* Add the items to the inventory. */
        for (ItemStack item : items) {
            inventory.addItem(item);
        }
    }

    public static void giveHeads(List<Head> heads, Player player) {
        giveHeads(heads, player, 1);
    }
}
