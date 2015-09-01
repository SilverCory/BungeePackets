package org.spawl.bungeepackets.packet;

import org.spawl.bungeepackets.BungeePackets;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants.Direction;

public abstract class ConditionalPacket extends Packet {
	
	public abstract boolean isCancelled(AbstractPacketHandler handler);
	
	private byte[] buffer;

	@Override
	public void read(ByteBuf buf, Direction direction, int protocolVersion) {
		buffer = BungeePackets.readAllBytes(buf);
	}

	@Override
	public void write(ByteBuf buf, Direction direction, int protocolVersion) {
		buf.writeBytes(buffer);
	}

	@Override
	public void handle(AbstractPacketHandler handler) throws Exception {
		if(!isCancelled(handler)) {
			BungeePackets.getUserConnection(handler).getServer().unsafe().sendPacket(this);
		}
	}

}
