package org.sct.supercrash.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.plugincore.util.function.command.SubCommand;
import org.sct.supercrash.SuperCrash;
import org.sct.supercrash.enumeration.LangType;
import org.sct.supercrash.file.Lang;

import java.util.Map;

/**
 * @author icestar
 * @date 2020/2/13 10:11
 */

public class Reload implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] strings) {
        if (!sender.isOp()) {
            sender.sendMessage(Lang.getString(LangType.LANG_NOPERMISSION));
        }

        Lang.loadLang();
        SuperCrash.getInstance().saveDefaultConfig();
        SuperCrash.getInstance().reloadConfig();

        sender.sendMessage(Lang.getString(LangType.LANG_RELOAD));
        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
