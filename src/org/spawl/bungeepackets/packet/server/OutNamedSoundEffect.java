package org.spawl.bungeepackets.packet.server;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.ProtocolConstants.Direction;

import org.spawl.bungeepackets.packet.StructurePacket;

public class OutNamedSoundEffect extends StructurePacket {
	
	public String name;
	private int x;
	private int y;
	private int z;
	private float volume;
	private int pitch;
	
	public OutNamedSoundEffect(){}
	
	public OutNamedSoundEffect(String name, double x, double y, double z, float volume, float pitch) {
		this.name = name;
		setX(x);
		setY(y);
		setZ(z);
		this.volume = volume;
		setPitch(pitch);
	}
	
	public void setVolume(float volume) {
		if(volume > 1)
			volume = 1;
		this.volume = volume;
	}
	
	public float getVolume() {
		return this.volume;
	}
	
	public float getPitch() {
		return this.pitch / 63.0F;
	}
	
	public void setPitch(float f) {
		this.pitch = ((int)(f * 63.0F));
	}
	
	public double getX() {
		return x / 8.0D;
	}
	
	public double getY() {
		return y / 8.0D;
	}
	
	public double getZ() {
		return z / 8.0D;
	}
	
	public void setX(double x) {
		this.x = ((int)(x * 8.0D));
	}
	
	public void setY(double y) {
		this.y = ((int)(y * 8.0D));
	}
	
	public void setZ(double z) {
		this.z = ((int)(z * 8.0D));
	}
	
	@Override
	public void read(ByteBuf buf, Direction direction, int protocolVersion) {
		this.name = readString(buf);
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.volume = buf.readFloat();
		this.pitch = buf.readUnsignedByte();
	}

	@Override
	public void write(ByteBuf buf, Direction direction, int protocolVersion) {
		writeString(this.name, buf);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeFloat(this.volume);
		buf.writeByte(this.pitch);
	}

}
