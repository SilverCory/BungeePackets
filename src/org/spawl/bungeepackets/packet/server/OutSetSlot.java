package org.spawl.bungeepackets.packet.server;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.ProtocolConstants.Direction;

import org.spawl.bungeepackets.item.ItemStack;
import org.spawl.bungeepackets.packet.StructurePacket;

public class OutSetSlot extends StructurePacket {

	public int windowID;
	public int slot;
	public ItemStack item;

	@Override
	public void read(ByteBuf buf, Direction direction, int protocolVersion) {
		this.windowID = buf.readByte();
		this.slot = buf.readShort();
		this.item = new ItemStack(buf);
	}

	@Override
	public void write(ByteBuf buf, Direction direction, int protocolVersion) {
		buf.writeByte(windowID);
		buf.writeShort(slot);
		if(item == null)
			buf.writeShort(-1);
		else
			item.write(buf);

	}

}
