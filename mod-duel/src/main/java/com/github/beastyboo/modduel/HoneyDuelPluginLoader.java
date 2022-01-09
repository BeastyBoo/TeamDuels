package com.github.beastyboo.modduel;

import com.github.beastyboo.HoneyDuel;
import com.github.beastyboo.HoneyDuelAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class HoneyDuelPluginLoader extends JavaPlugin implements HoneyDuel {

    private HoneyDuelCore core;

    @Override
    public synchronized void onEnable() {
        core = new HoneyDuelCore(this);
        core.load();

    }

    @Override
    public synchronized void onDisable() {
        core.close();
        core = null;
    }

    @Override
    public HoneyDuelAPI getAPI() {
        return core;
    }
}
