package org.spawl.bungeepackets.packet.server;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants.Direction;

import org.spawl.bungeepackets.packet.StructurePacket;

public class OutOpenWindow extends StructurePacket {
	
	public int id;
	public String windowType;
	public String title;
	public int slots;
	
	/** The entity ID is only used for entities like horses. */
	public int entityID;
	
	@Override
	public void read(ByteBuf buf, Direction direction, int protocolVersion) {
		this.id = buf.readByte();
		this.windowType = DefinedPacket.readString(buf);
		this.title = DefinedPacket.readString(buf);
		this.slots = buf.readUnsignedByte();
	}

	@Override
	public void write(ByteBuf buf, Direction direction, int protocolVersion) {
		buf.writeByte(id);
		DefinedPacket.writeString(windowType, buf);
		DefinedPacket.writeString(title, buf);
		buf.writeByte(slots);
	}

}
