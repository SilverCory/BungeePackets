package org.spawl.bungeepackets.util;

import java.lang.reflect.Method;

import net.md_5.bungee.UserConnection;
import net.md_5.bungee.connection.DownstreamBridge;
import net.md_5.bungee.connection.UpstreamBridge;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.Protocol.DirectionData;

import org.spawl.bungeepackets.packet.Packet;

public class Util {

	public static UserConnection getConnection(AbstractPacketHandler handler) {
		if(handler instanceof DownstreamBridge) {
			DownstreamBridge bridge = (DownstreamBridge) handler;
			UserConnection con = (UserConnection) Reflection.get(bridge, "con");
			return con;
		}

		if(handler instanceof UpstreamBridge) {
			UpstreamBridge bridge = (UpstreamBridge) handler;
			UserConnection con = (UserConnection) Reflection.get(bridge, "con");
			return con;
		}

		return null;
	}

	public static boolean registerPacket(DirectionData data, int id, Class<? extends Packet> packetClass) {
		try{
			Method registerPacket = data.getClass().getDeclaredMethod("registerPacket", int.class, Class.class);
			registerPacket.setAccessible(true);
			registerPacket.invoke(data, id, packetClass);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
