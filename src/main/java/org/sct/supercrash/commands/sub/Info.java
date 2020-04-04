package org.sct.supercrash.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.plugincore.util.function.command.SubCommand;
import org.sct.supercrash.SuperCrash;

import java.util.Map;

/**
 * @author icestar
 * @date 2020/2/13 10:11
 */

public class Info implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] strings) {
        sender.sendMessage("§7┌ §ePlugin§7:§b SuperCrash");
        sender.sendMessage("§7├ §eAuthor§7:§b 冰星");
        sender.sendMessage("§7├ §eVersion§7:§b " + SuperCrash.getInstance().getDescription().getVersion());
        sender.sendMessage("§7└ §eLink§7:§b None");

        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
