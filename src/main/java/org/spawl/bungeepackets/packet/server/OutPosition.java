package org.spawl.bungeepackets.packet.server;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.ProtocolConstants.Direction;

import org.spawl.bungeepackets.packet.StructurePacket;
import org.spawl.bungeepackets.util.Position;

public class OutPosition extends StructurePacket {
	
	public double x,y,z;
	public float yaw,pitch;
	private byte value;
	
	public OutPosition() {
		value = 0;
	}
	
	public OutPosition(Position p) {
		this.x = p.getX();
		this.y = p.getY();
		this.z = p.getZ();
		this.yaw = p.getYaw();
		this.pitch = p.getPitch();
		this.value = 0;
	}

	@Override
	public void read(ByteBuf buf, Direction direction, int protocolVersion) {
		this.x = buf.readDouble();
		this.y = buf.readDouble();
		this.z = buf.readDouble();
		this.yaw = buf.readFloat();
		this.pitch = buf.readFloat();
		this.value = buf.readByte();
	}

	@Override
	public void write(ByteBuf buf, Direction direction, int protocolVersion) {
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
		buf.writeFloat(yaw);
		buf.writeFloat(pitch);
		buf.writeByte(value);
	}

}
