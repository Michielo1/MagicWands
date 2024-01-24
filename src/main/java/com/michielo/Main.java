package com.michielo;

import com.michielo.bukkit.commands.CommandHandler;
import com.michielo.bukkit.listeners.ClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Starting MagicWands...");

        instance = this;
        this.getCommand("magicwands").setExecutor(new CommandHandler());
        getServer().getPluginManager().registerEvents(new ClickListener(), this);

        Bukkit.getLogger().info("Started!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Closing MagicWands...");
    }

}