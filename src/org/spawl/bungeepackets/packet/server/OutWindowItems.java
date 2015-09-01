package org.spawl.bungeepackets.packet.server;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.ProtocolConstants.Direction;

import org.spawl.bungeepackets.item.ItemStack;
import org.spawl.bungeepackets.packet.StructurePacket;

public class OutWindowItems extends StructurePacket {
	
	public int id;
	public ItemStack[] items;

	@Override
	public void read(ByteBuf buf, Direction direction, int protocolVersion) {
		this.id = buf.readUnsignedByte();
		int i = buf.readShort();
		this.items = new ItemStack[i];
		for(int j = 0; j < i; j++)
			this.items[j] = new ItemStack(buf);
	}

	@Override
	public void write(ByteBuf buf, Direction direction, int protocolVersion) {
		buf.writeByte(id);
		buf.writeShort(items.length);
		for(ItemStack stack : items)
			stack.write(buf);
	}

}
