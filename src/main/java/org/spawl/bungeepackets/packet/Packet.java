package org.spawl.bungeepackets.packet;

import org.spawl.bungeepackets.BungeePackets;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;

public abstract class Packet extends DefinedPacket {
	
	@Override
    public abstract void read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion);

    @Override
    public abstract void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion);

	public abstract void handle(ProxiedPlayer player) throws Exception;

	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public void handle(AbstractPacketHandler handler) throws Exception {
		handle(BungeePackets.getUserConnection(handler));
	}

	@Override
	public String toString() {
		return "Custom Packet Class";
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

}
