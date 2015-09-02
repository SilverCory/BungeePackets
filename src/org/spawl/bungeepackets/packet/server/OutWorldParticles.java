package org.spawl.bungeepackets.packet.server;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.ProtocolConstants.Direction;

import org.spawl.bungeepackets.packet.StructurePacket;

public class OutWorldParticles extends StructurePacket {
	
	public int particleID;
	public boolean longDistance;
	public float x;
	public float y;
	public float z;
	public float offsetX;
	public float offsetY;
	public float offsetZ;
	public float speed;
	public int count;
	public int[] data;

	@Override
	public void read(ByteBuf buf, Direction direction, int protocolVersion) {
		this.particleID = buf.readInt();
		this.longDistance = buf.readBoolean();
		this.x = buf.readFloat();
		this.y = buf.readFloat();
		this.z = buf.readFloat();
		this.offsetX = buf.readFloat();
		this.offsetY = buf.readFloat();
		this.offsetZ = buf.readFloat();
		this.speed = buf.readFloat();
		this.count = buf.readInt();
		int amount = 0;
		if(particleID == 36)
			amount = 2;
		if(particleID == 37 || particleID == 38)
			amount = 1;
		this.data = new int[amount];
		for(int i = 0; i < amount; i++)
			this.data[i] = readVarInt(buf);
	}

	@Override
	public void write(ByteBuf buf, Direction direction, int protocolVersion) {
		buf.writeInt(particleID);
		buf.writeBoolean(longDistance);
		buf.writeFloat(x);
		buf.writeFloat(y);
		buf.writeFloat(z);
		buf.writeFloat(offsetX);
		buf.writeFloat(offsetY);
		buf.writeFloat(offsetZ);
		buf.writeFloat(speed);
		buf.writeInt(count);
		int amount = 0;
		if(particleID == 36)
			amount = 2;
		if(particleID == 37 || particleID == 38)
			amount = 1;
		for(int i = 0; i < amount; i++)
			writeVarInt(this.data[i], buf);
	}

}
