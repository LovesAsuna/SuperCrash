package org.sct.supercrash.commands;

import com.google.common.collect.Maps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.sct.plugincore.util.function.SubCommand;
import org.sct.supercrash.SuperCrash;
import org.sct.supercrash.commands.sub.Crash;
import org.sct.supercrash.commands.sub.Info;
import org.sct.supercrash.commands.sub.Reload;
import org.sct.supercrash.enumeration.LangType;
import org.sct.supercrash.file.Lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author icestar
 * @date 2020/2/13 10:10
 */

public class CommandHandler implements CommandExecutor, TabCompleter {
    protected static final String cmds = "supercrash";
    private Map<String, SubCommand> subCommandMap = Maps.newHashMap();

    public CommandHandler() {
        subCommandMap.put("reload",new Reload());
        subCommandMap.put("info",new Info());
        subCommandMap.put("crash",new Crash());
    }

    public void registerSubCommand(String commandName, SubCommand command) {
        if (subCommandMap.containsKey(commandName)) {
            SuperCrash.getInstance().getLogger().warning("发现重复子命令注册!");
        }
        subCommandMap.put(commandName, command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmds.equalsIgnoreCase(cmd.getName())) {
            if(args.length == 0) {//如果命令没有参数
                subCommandMap.get("info").execute(sender, args);
                return true;
            }

            SubCommand subCommand = subCommandMap.get(args[0]);
            if (subCommand == null) {//如果参数不正确
                sender.sendMessage(Lang.getString(LangType.LANG_COMMANDERROR));
                return true;
            }
            subCommand.execute(sender, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase(cmds)) {
            if (args.length == 1) {
                completions.add("reload");
                completions.add("crash");
                completions.add("info");
                return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
            }

        }
        return completions;
    }
}