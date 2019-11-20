package com.tweska.sweetheads;

import com.tweska.sweetheads.commands.TestCommand;
import com.tweska.sweetheads.scraper.MinecraftHeadsScraper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class SweetHeadsPlugin extends JavaPlugin {
    private PluginManager pluginManager;

    public ArrayList<ItemStack> heads = new ArrayList<>();

    @Override
    public void onEnable() {
        /* Grab the instance of the plugin manager. */
        pluginManager = getServer().getPluginManager();

        this.getCommand("shtest").setExecutor(new TestCommand(this));

        ArrayList<String> uuids = MinecraftHeadsScraper.doSomething(getLogger());

        for (String uuid : uuids) {
            ItemStack head = SweetHeadSpawner.getCustomHead(uuid);
            heads.add(head);

        }
    }

}
