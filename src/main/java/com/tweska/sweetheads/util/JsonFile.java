package com.tweska.sweetheads.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class JsonFile<T> {
    private static Gson gson;
    private File file;

    private final Type type;

    public JsonFile(JavaPlugin plugin, String fileName, Type type, boolean prettyPrinting) {
        /* Create a new GsonBuilder. */
        if (prettyPrinting) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        } else {
            gson = new GsonBuilder().create();
        }

        /* Create the File object for JSON file. */
        file = new File(plugin.getDataFolder(), fileName);

        /* If the file does not exist yet, create the default file. */
        if (!file.exists()) {
            plugin.saveResource(file.getName(), false);
        }

        this.type = type;
    }

    public JsonFile(JavaPlugin plugin, String fileName, Type type) {
        this(plugin, fileName, type, false);
    }

    public T loadFile() throws FileNotFoundException {
        /* Read the JSON file. */
        return gson.fromJson(new FileReader(file), type);

    }

    public void saveFile(Object content) throws IOException {
        /* Convert map into JSON file. */
        final String json = gson.toJson(content);

        /* Delete the existing file. */
        file.delete();

        /* Create a new file with the updated data. */
        Files.write(file.toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }
}
