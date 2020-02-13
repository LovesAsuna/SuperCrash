package org.sct.supercrash.commands.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import org.bukkit.entity.Player;
import org.sct.plugincore.util.BasicUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * @author icestar
 * @date 2020/2/13 12:01
 */

public class TitleUtil {
    public static boolean sendTitle(Player player, String text, PacketPlayOutTitle.EnumTitleAction action) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = manager.createPacket(PacketType.Play.Server.TITLE);

        packet.getEnumModifier(PacketPlayOutTitle.EnumTitleAction.class, 0).write(0, action);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(BasicUtil.convert(text)));
        packet.getIntegers().write(0, -1);
        packet.getIntegers().write(1, -1);
        packet.getIntegers().write(2, -1);

        try {
            manager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            return false;
        }
        return true;
    }
}
