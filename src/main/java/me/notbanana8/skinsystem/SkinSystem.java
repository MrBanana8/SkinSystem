package me.notbanana8.skinsystem;

import me.notbanana8.skinsystem.commands.CommandManager;
import me.notbanana8.skinsystem.utils.SkinStorageUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class SkinSystem extends JavaPlugin {

    private static SkinSystem plugin;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Gui(), this);
        getCommand("skins").setExecutor(new CommandManager());
        plugin = this;

        try {
            SkinStorageUtil.loadSkins();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SkinSystem getPlugin() {
        return plugin;
    }
}
