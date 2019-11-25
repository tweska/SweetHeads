package com.tweska.sweetheads.util;

import com.google.common.reflect.TypeToken;
import com.tweska.sweetheads.heads.Head;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class HeadsFile extends JsonFile<List<Head>> {
    public HeadsFile(JavaPlugin plugin, String fileName) {
        super(plugin, fileName, new TypeToken<List<Head>>(){}.getType(), true);
    }

    public HeadsFile(JavaPlugin plugin) {
        this(plugin, "heads-cache.json");
    }

    @Override
    public List<Head> loadFile() {
        try {
            return super.loadFile();
        } catch (FileNotFoundException exception) {
            return new ArrayList<>();
        }
    }
}
