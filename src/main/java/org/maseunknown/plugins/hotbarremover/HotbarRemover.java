package org.maseunknown.plugins.hotbarremover;

import org.bukkit.plugin.java.JavaPlugin;

public final class HotbarRemover extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginCommand("hotbarremover").setExecutor(new HotbarRemoverCommand(this));
        getCommand("hotbarremover").setTabCompleter(new HotbarRemoverCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
