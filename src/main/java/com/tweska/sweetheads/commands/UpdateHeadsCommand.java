package com.tweska.sweetheads.commands;

import com.tweska.sweetheads.SweetHeadsPlugin;
import com.tweska.sweetheads.SweetHeadsUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class UpdateHeadsCommand implements CommandExecutor {

    private SweetHeadsPlugin plugin;

    public UpdateHeadsCommand(SweetHeadsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SweetHeadsUtil.getInstance().updateHeads();
                } catch (IOException e) {
                    sender.sendMessage(String.format("%s IO exception while downloading heads!", SweetHeadsPlugin.chatPrefix));
                    plugin.getLogger().severe("IO exception while downloading heads!");
                }

                try {
                    SweetHeadsUtil.getInstance().saveHeads();
                } catch (IOException e) {
                    sender.sendMessage(String.format("%s IO exception while saving heads!"));
                    plugin.getLogger().severe("IO exception while saving heads!");
                }
            }
        });

        thread.start();

        return true;
    }
}
