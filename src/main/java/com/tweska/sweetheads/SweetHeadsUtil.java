package com.tweska.sweetheads;

import com.tweska.sweetheads.heads.Head;
import com.tweska.sweetheads.scraper.MinecraftHeadsScraper;
import com.tweska.sweetheads.scraper.Scraper;
import com.tweska.sweetheads.util.HeadsFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SweetHeadsUtil {
    private static SweetHeadsUtil sweetHeadsUtil = new SweetHeadsUtil();

    private JavaPlugin plugin;
    private List<Scraper> scrapers = new ArrayList<>();
    private List<Head> heads = new ArrayList<>();
    private HeadsFile headsFile;


    private SweetHeadsUtil() {
        scrapers.add(new MinecraftHeadsScraper());
    }

    public static SweetHeadsUtil getInstance() {
        return sweetHeadsUtil;
    }

    protected void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
        headsFile = new HeadsFile(plugin);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void loadHeads() {
        headsFile.loadFile();
    }

    public void saveHeads() throws IOException {
        headsFile.saveFile(heads);
    }

    public void updateHeads() throws IOException {
        List<Head> newHeads = new ArrayList<>();

        for (Scraper scraper : scrapers) {
            newHeads.addAll(scraper.scrape(plugin.getLogger()));
        }

        heads.clear();
        heads.addAll(newHeads);
    }

    public Head findFirst(String name) {
        for (Head head : heads) {
            if (!head.getName().contains(name)) {
                continue;
            }

            return head;
        }

        return null;
    }
}
