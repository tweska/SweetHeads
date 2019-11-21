package com.tweska.sweetheads;

import com.tweska.sweetheads.bukkit.HeadSpawner;
import com.tweska.sweetheads.heads.Head;
import com.tweska.sweetheads.util.HeadsFile;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class SweetHeads extends JavaPlugin {
    private PluginManager pluginManager;

    private HeadsFile headsFile;

    @Override
    public void onEnable() {
        /* Grab the instance of the plugin manager. */
        pluginManager = getServer().getPluginManager();

        headsFile = new HeadsFile(this, "heads-cache.json");

        for (Player player : getServer().getOnlinePlayers()) {
            for (Head head : getHeads()) {
                player.getInventory().addItem(HeadSpawner.getItemStack(head));
            }
        }
    }

    List<Head> getHeads() {
        final List<Head> heads = headsFile.loadFile();
        return heads;
    }
}
