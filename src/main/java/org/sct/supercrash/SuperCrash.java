package org.sct.supercrash;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.supercrash.commands.CommandHandler;
import org.sct.supercrash.file.Lang;

public final class SuperCrash extends JavaPlugin {
    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        Lang.loadLang();

        Bukkit.getPluginCommand("supercrash").setExecutor(new CommandHandler());

        Bukkit.getConsoleSender().sendMessage("§7[§eSuperCrash§7]§2插件已启用");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§7[§eSuperCrash§7]§c插件已卸载");
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
