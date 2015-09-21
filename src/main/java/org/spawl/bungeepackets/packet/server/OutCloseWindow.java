package org.spawl.bungeepackets.packet.server;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.ProtocolConstants.Direction;

import org.spawl.bungeepackets.packet.StructurePacket;

public class OutCloseWindow extends StructurePacket {

	public int id;
	
	public OutCloseWindow(int id) {
		this.id = id;
	}
	
	@Override
	public void read(ByteBuf buf, Direction direction, int protocolVersion) {
		this.id = buf.readUnsignedByte();
	}

	@Override
	public void write(ByteBuf buf, Direction direction, int protocolVersion) {
		buf.writeByte(id);
	}

}
