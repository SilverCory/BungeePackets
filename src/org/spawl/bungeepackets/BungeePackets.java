package org.spawl.bungeepackets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.lang.reflect.Method;

import net.md_5.bungee.ServerConnection;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.Protocol;

import org.spawl.bungeepackets.encoder.CustomDecoder;
import org.spawl.bungeepackets.encoder.CustomEncoder;
import org.spawl.bungeepackets.inventory.Inventory;
import org.spawl.bungeepackets.packet.Packet;
import org.spawl.bungeepackets.packet.client.InCloseWindow;
import org.spawl.bungeepackets.packet.client.InWindowClick;
import org.spawl.bungeepackets.packet.server.OutCloseWindow;
import org.spawl.bungeepackets.packet.server.OutOpenWindow;
import org.spawl.bungeepackets.packet.server.OutSetSlot;
import org.spawl.bungeepackets.packet.server.OutWindowItems;
import org.spawl.bungeepackets.util.Reflection;
import org.spawl.bungeepackets.util.Util;

public class BungeePackets extends Plugin implements Listener {

	public static byte[] readAllBytes(ByteBuf buffer) {
		byte[] b = new byte[buffer.readableBytes()];
		buffer.readBytes(b);
		return b;
	}

	public static UserConnection getUserConnection(AbstractPacketHandler handler) {
		return Util.getConnection(handler);
	}

	public static boolean registerPacket(Protocol.DirectionData data, int id, Class<? extends Packet> cl) {
		try{
			Util.registerPacket(data, id, cl);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public void onEnable() {
		registerPacket(Protocol.GAME.TO_SERVER, 13, InCloseWindow.class);
		registerPacket(Protocol.GAME.TO_SERVER, 14, InWindowClick.class);
		registerPacket(Protocol.GAME.TO_CLIENT, 46, OutCloseWindow.class);
		registerPacket(Protocol.GAME.TO_CLIENT, 45, OutOpenWindow.class);
		registerPacket(Protocol.GAME.TO_CLIENT, 47, OutSetSlot.class);
		registerPacket(Protocol.GAME.TO_CLIENT, 48, OutWindowItems.class);

		this.getProxy().getPluginManager().registerListener(this, new Inventory());
		this.getProxy().getPluginManager().registerListener(this, this);
	}

	@EventHandler
	public void onServerConnected(ServerConnectedEvent event) {
		ProxiedPlayer p = event.getPlayer();
		ServerConnection server = (ServerConnection) event.getServer();

		if(p != null)
			if(server != null) {
				ChannelWrapper wrapper = server.getCh();
				if(wrapper != null)
				{
					try{
						wrapper.getHandle().pipeline().addAfter(PipelineUtils.PACKET_DECODER, "custom-decoder", new CustomDecoder(true, p));
						wrapper.getHandle().pipeline().addAfter(PipelineUtils.PACKET_ENCODER, "custom-encoder", new CustomEncoder(true, p));
					}catch(Exception e) {
						System.out.println("[BungeePackets] Failed to inject server connection for "+event.getPlayer().getName());
					}
				}
			}
	}

	@EventHandler
	public void onPostLogin(PostLoginEvent event) {
		try{
			ProxiedPlayer p = event.getPlayer();
			Object ch = Reflection.get(p, "ch");
			Method method = ch.getClass().getDeclaredMethod("getHandle", new Class[0]);
			Channel channel = (Channel)method.invoke(ch, new Object[0]);
			channel.pipeline().addAfter(PipelineUtils.PACKET_DECODER, "custom-decoder", new CustomDecoder(false, p));
			channel.pipeline().addAfter(PipelineUtils.PACKET_ENCODER, "custom-encoder", new CustomEncoder(false, p));
		}catch(Exception e) {
			System.out.println("[BungeePackets] Failed to inject client connection for "+event.getPlayer().getName());
		}
	}
}
