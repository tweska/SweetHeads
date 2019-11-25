package com.tweska.sweetheads;

import com.tweska.sweetheads.commands.UpdateHeadsCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class SweetHeadsPlugin extends JavaPlugin {
    private PluginManager pluginManager;

    public static final String chatPrefix = "[SweetHeads]";

    @Override
    public void onEnable() {
        /* Set the parent plugin of the SweetHeadsUtil instance. */
        SweetHeadsUtil.getInstance().setPlugin(this);

        /* Grab the instance of the plugin manager. */
        pluginManager = getServer().getPluginManager();

        /* Register the commands. */
        getCommand("updateheads").setExecutor(new UpdateHeadsCommand(this));
    }

    @Override
    public void onDisable() {
        try {
            SweetHeadsUtil.getInstance().saveHeads();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SweetHeadsUtil getSweetHeadsUtilInstance() {
        return SweetHeadsUtil.getInstance();
    }
}
