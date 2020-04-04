package org.sct.supercrash.commands;



import org.bukkit.plugin.java.JavaPlugin;
import org.sct.plugincore.util.function.command.CommandHandler;
import org.sct.supercrash.commands.sub.Crash;
import org.sct.supercrash.commands.sub.Info;
import org.sct.supercrash.commands.sub.Reload;

/**
 * @author icestar
 * @date 2020/2/13 10:10
 */

public class SubCommandHandler extends CommandHandler {
    public SubCommandHandler(JavaPlugin instance, String cmd) {
        super(instance, cmd);
        registerSubCommand("crash", new Crash());
        registerSubCommand("info", new Info());
        registerSubCommand("reload", new Reload());
    }
}