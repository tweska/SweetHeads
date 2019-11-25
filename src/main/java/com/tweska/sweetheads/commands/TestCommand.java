package com.tweska.sweetheads.commands;

import com.tweska.sweetheads.SweetHeadsPlugin;
import com.tweska.sweetheads.SweetHeadsUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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

        final Player player = (Player) sender;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SweetHeadsUtil.getInstance().updateHeads();
                } catch (IOException e) {
                    player.sendMessage("IO exception while updating heads!");
                    plugin.getLogger().severe("IO exception while updating heads!");
                }
            }
        });

        return true;
    }
}
