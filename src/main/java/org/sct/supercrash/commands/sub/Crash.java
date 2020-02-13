package org.sct.supercrash.commands.sub;

import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.plugincore.util.function.SubCommand;
import org.sct.supercrash.SuperCrash;
import org.sct.supercrash.commands.util.CrashUtil;
import org.sct.supercrash.commands.util.TitleUtil;
import org.sct.supercrash.enumeration.ConfigType;
import org.sct.supercrash.enumeration.LangType;
import org.sct.supercrash.file.Config;
import org.sct.supercrash.file.Lang;


/**
 * @author icestar
 * @date 2020/2/13 10:20
 */

public class Crash implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Lang.getString(LangType.LANG_NOPERMISSION));
            return false;
        }

        /*supercrash crash player text subtext*/
        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(Lang.getString(LangType.LANG_NOTEXISTPLAYER));
            return false;
        }

        String text = null;
        String subtext = null;
        if (args.length == 2) {
            text = Config.getString(ConfigType.SETTING_DEFAULTMSG);
            subtext = Config.getString(ConfigType.SETTING_DEFAULTSUBMSG);
        } else if (args.length == 3) {
            text = args[2];
            subtext = Config.getString(ConfigType.SETTING_DEFAULTSUBMSG);
        } else {
            text = args[2];
            subtext = args[3];
        }


        TitleUtil.sendTitle(player, text, PacketPlayOutTitle.EnumTitleAction.TITLE);
        TitleUtil.sendTitle(player, subtext, PacketPlayOutTitle.EnumTitleAction.SUBTITLE);

        if (Config.getBoolean(ConfigType.SETTING_CRASH)) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(SuperCrash.getInstance(), () -> {
                CrashUtil.crash(player);
            }, 10L);
        }

        Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), text, null, sender.getName());
        sender.sendMessage(Lang.getString(LangType.LANG_SUCCESSSEND));

        return true;
    }
}
