package com.tweska.sweetheads.commands;

import com.tweska.sweetheads.SweetHeadsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {
    private SweetHeadsPlugin plugin;

    public TestCommand(SweetHeadsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s,  @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();

        for (ItemStack head : plugin.heads) {
            inventory.addItem(head);
        }

        return true;
    }
}
