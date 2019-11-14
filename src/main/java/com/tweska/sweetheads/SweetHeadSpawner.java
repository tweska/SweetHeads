package com.tweska.sweetheads;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public abstract class SweetHeadSpawner {

    public static ItemStack getPlayerHead(@NotNull OfflinePlayer player) {
        return getPlayerHead(player, String.format("%s's Head", player.getName()));
    }

    public static ItemStack getPlayerHead(@NotNull OfflinePlayer player, @NotNull String name) {
        return getPlayerHead(player, name, 1);
    }

    public static ItemStack getPlayerHead(@NotNull OfflinePlayer player, @NotNull String name, String... lore) {
        return getPlayerHead(player, name, 1, lore);
    }

    public static ItemStack getPlayerHead(@NotNull OfflinePlayer player, @NotNull String name, int amount, String... lore) {
        /* Get a new head and it's metadata. */
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        /* Set the display name and the owner of this head. */
        meta.setDisplayName(name);
        meta.setOwningPlayer(Bukkit.getPlayer(player.getUniqueId()));

        /* Add the lore to this head. */
        ArrayList<String> lores = new ArrayList<>(Arrays.asList(lore));
        meta.setLore(lores);

        /* Update the metadata of the head. */
        head.setItemMeta(meta);

        return head;
    }

    public static ItemStack getCustomHead(@NotNull String textures) {
        return getCustomHead(textures, "Custom Head");
    }

    public static ItemStack getCustomHead(@NotNull String textures, String name) {
        return getCustomHead(textures, name, 1);
    }

    public static ItemStack getCustomHead(@NotNull String textures, @NotNull String name, String... lore) {
        return getCustomHead(textures, name, 1, lore);
    }

    public static ItemStack getCustomHead(@NotNull String textures, @NotNull String name, int amount, String... lore) {
        /* Get a new head item. */
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, amount);

        /* If no texture is given, return the default head. */
        if(textures.isEmpty()) {
            return head;
        }

        /* Get the metadata of the head. */
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        /* Get a new dummy game profile. */
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        /* Set the display name of the head. */
        meta.setDisplayName(name);

        /* Add the lore. */
        ArrayList<String> lores = new ArrayList<>(Arrays.asList(lore));
        meta.setLore(lores);

        /* Add the texture to the dummy game profile. */
        profile.getProperties().put("textures", new Property("textures", textures));

        /* Attempt to set the profile field of the metadata. */
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException|IllegalAccessException exception) {
            exception.printStackTrace();
        }

        /* Update the meta data of the head. */
        head.setItemMeta(meta);

        return head;
    }
}
