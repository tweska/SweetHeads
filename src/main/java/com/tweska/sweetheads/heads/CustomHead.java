package com.tweska.sweetheads.heads;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class CustomHead extends ItemStack {
    public CustomHead(String displayName, String uuid, String value, int amount, String... lore) {
        /* Set the type of this item to a player head. */
        setType(Material.PLAYER_HEAD);

        /* Set the specified amount. */
        setAmount(amount);

        /* Get the meta data of this item. */
        SkullMeta meta = (SkullMeta) getItemMeta();

        /* Get the game profile that corresponds to the right UUID. */
        GameProfile profile = new GameProfile(UUID.fromString(uuid), null);

        /* Set the display name of this item. */
        meta.setDisplayName(displayName);

        /* Set the lore of this item. */
        meta.setLore(Arrays.asList(lore));

        /* Set the textures of this item. */
        profile.getProperties().put("textures", new Property("textures", value));

        /* Attempt to set the profile field of the metadata of this item. */
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException|IllegalAccessException exception) {
            exception.printStackTrace();
        }

        /* Update the metadata of this item. */
        setItemMeta(meta);
    }

    public CustomHead(String displayName, String uuid, String value, String... lore) {
        this(displayName, uuid, value, 1, lore);
    }

    public CustomHead(String uuid, String value, int amount) {
        this("Custom Head", uuid, value, amount);
    }

    public CustomHead(String uuid, String value) {
        this("Custom Head", uuid, value);
    }
}
