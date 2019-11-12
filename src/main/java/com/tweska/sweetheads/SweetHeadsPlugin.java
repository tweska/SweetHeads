package com.tweska.sweetheads;

import com.tweska.sweetheads.commands.TestCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SweetHeadsPlugin extends JavaPlugin {
    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        /* Grab the instance of the plugin manager. */
        pluginManager = getServer().getPluginManager();

        this.getCommand("shtest").setExecutor(new TestCommand(this));
    }

}
