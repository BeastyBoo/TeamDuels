package com.github.beastyboo.modduel;

import com.github.beastyboo.HoneyDuelAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class HoneyDuelCore implements HoneyDuelAPI {

    private final JavaPlugin plugin;
    private final Logger logger;

    public HoneyDuelCore(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = plugin.getLogger();
    }

    void load() {

    }

    void close() {

    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }
}
