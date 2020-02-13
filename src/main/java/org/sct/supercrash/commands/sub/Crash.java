package org.sct.supercrash.commands.sub;

import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.plugincore.util.function.SubCommand;
import org.sct.supercrash.SuperCrash;
import org.sct.supercrash.commands.util.CrashUtil;
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

        /*supercrash crash player text*/
        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            sender.sendMessage(Lang.getString(LangType.LANG_NOTEXISTPLAYER));
            return false;
        }

        String text = null;
        if (args.length == 2) {
            text = Config.getString(ConfigType.SETTING_DEFAULTMSG);
        } else {
            text = args[2];
        }

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = manager.createPacket(PacketType.Play.Server.TITLE);

        packet.getEnumModifier(PacketPlayOutTitle.EnumTitleAction.class, 0).write(0, PacketPlayOutTitle.EnumTitleAction.TITLE);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(BasicUtil.convert(text)));
        packet.getIntegers().write(0, -1);
        packet.getIntegers().write(1, -1);
        packet.getIntegers().write(2, -1);

        try {
            manager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Bukkit.getScheduler().runTaskLaterAsynchronously(SuperCrash.getInstance(), () -> {
            CrashUtil.crash(player);
        }, 10L);
        sender.sendMessage(Lang.getString(LangType.LANG_SUCCESSSEND));

        return true;
    }
}
