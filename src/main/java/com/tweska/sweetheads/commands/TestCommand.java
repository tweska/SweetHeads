package com.tweska.sweetheads.commands;

import com.tweska.sweetheads.SweetHeadSpawner;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class TestCommand implements CommandExecutor {
    Logger logger;

    public TestCommand(JavaPlugin plugin) {
        logger = plugin.getLogger();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s,  @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();

        ItemStack playerHead = SweetHeadSpawner.getPlayerHead(Bukkit.getOfflinePlayer(player.getUniqueId()));
        ItemStack customHead1 = SweetHeadSpawner.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg5MjJjODQyM2QyODExODQwZjU2Yjc1YzQ1M2FlM2UyYmIzN2ZhMWQ3ZDIzNDhhNWVmOWI0NDU2YzM1ODIxYyJ9fX0=", "Bread");
        ItemStack customHead2 = SweetHeadSpawner.getCustomHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFkMDk0ZGNhNTk4N2EwYmEyYzNmOThhMmM4OTgxYTg2ZjY4YjBmMDNiMGRkZGE1NTBkMDlmMWRlZmJjZjIwNyJ9fX0=", "Plush Steve");

        inventory.addItem(playerHead);
        inventory.addItem(customHead1);
        inventory.addItem(customHead2);

        return true;
    }
}
