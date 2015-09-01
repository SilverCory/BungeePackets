package org.spawl.bungeepackets.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.protocol.DefinedPacket;

import org.spawl.bungeepackets.connection.BungeeConnection;
import org.spawl.bungeepackets.event.PacketEvent;

public class CustomEncoder extends MessageToMessageEncoder<DefinedPacket> {

	private boolean server;
	private ProxiedPlayer p;

	public CustomEncoder(boolean server, ProxiedPlayer p) {
		this.server = server;
		this.p = p;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, DefinedPacket msg, List<Object> out)
			throws Exception {
		//Packet send event. Either the server the client is connecting to is sending the packet or Bungee is.
		PacketEvent event = null;

		if(server) {
			event = new PacketEvent(msg, p, new BungeeConnection(), p);
		}else{
			if(p instanceof UserConnection) {
				UserConnection u = (UserConnection) p;
				event = new PacketEvent(msg, p, new BungeeConnection(), u.getServer());
			}
		}

		if(event != null)
		{
			ProxyServer.getInstance().getPluginManager().callEvent(event);
		}

		boolean cancel = false;
		if(event != null)
			cancel = event.isCancelled();

		if(!cancel)
			out.add(msg);
	}

}
