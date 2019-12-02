package com.tweska.sweetheads;

import com.tweska.sweetheads.commands.FindHeadCommand;
import com.tweska.sweetheads.commands.FindHeadsCommand;
import com.tweska.sweetheads.commands.UpdateHeadsCommand;
import com.tweska.sweetheads.inventorygui.SearchResultGUIListener;
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

        pluginManager.registerEvents(new SearchResultGUIListener(), this);

        /* Register the commands. */
        getCommand("updateheads").setExecutor(new UpdateHeadsCommand(this));
        getCommand("findhead").setExecutor(new FindHeadCommand());
        getCommand("findheads").setExecutor(new FindHeadsCommand());
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
