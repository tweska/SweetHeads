package com.tweska.sweetheads.commands;

import com.tweska.sweetheads.SweetHeadsUtil;
import com.tweska.sweetheads.heads.Head;
import com.tweska.sweetheads.inventorygui.SearchResultGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FindHeadsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            return false;
        }

        List<Head> heads = SweetHeadsUtil.getInstance().findAll(args[0]);

        if (heads.size() == 0) {
            player.sendMessage(String.format("No custom heads could be found for \"%s\"", args[0]));
            return true;
        }

        SearchResultGUI gui = new SearchResultGUI(args[0], heads);
        gui.openGUI(player);

        return true;
    }
}
