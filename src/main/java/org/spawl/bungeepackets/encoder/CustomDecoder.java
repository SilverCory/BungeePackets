package org.spawl.bungeepackets.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import org.spawl.bungeepackets.connection.BungeeConnection;
import org.spawl.bungeepackets.event.PacketEvent;

import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.protocol.PacketWrapper;

public class CustomDecoder extends MessageToMessageDecoder<PacketWrapper> {

	private boolean server;
	private ProxiedPlayer p;

	public CustomDecoder(boolean server, ProxiedPlayer player) {
		this.server = server;
		this.p = player;
	}

	@Override
	protected void decode(ChannelHandlerContext chx, PacketWrapper wrapper,
			List<Object> out) throws Exception {
		//Packet receive event. This can either be from the server or client!

		if(wrapper.packet == null)
		{
			out.add(wrapper);
			return;
		}

		PacketEvent event = null;

		if(server) {
			if(p instanceof UserConnection) {
				UserConnection u = (UserConnection) p;
				event = new PacketEvent(wrapper.packet, p, u.getServer(), new BungeeConnection());
			}
		}else{
			event = new PacketEvent(wrapper.packet, p, p, new BungeeConnection());
		}

		if(event != null)
		{
			ProxyServer.getInstance().getPluginManager().callEvent(event);
		}

		boolean cancel = false;
		if(event != null)
			cancel = event.isCancelled();

		if(!cancel)
			out.add(wrapper);
	}

}
