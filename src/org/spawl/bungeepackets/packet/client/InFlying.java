package org.spawl.bungeepackets.packet.client;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.ProtocolConstants.Direction;

import org.spawl.bungeepackets.packet.StructurePacket;

public class InFlying extends StructurePacket {

	public double x, y, z;
	public float yaw, pitch;
	public boolean f, hasPos, hasLook;

	public InFlying() {
		this.x = Integer.MIN_VALUE;
		this.y = Integer.MIN_VALUE;
		this.z = Integer.MIN_VALUE;
		this.yaw = Float.MIN_VALUE;
		this.pitch = Float.MIN_VALUE;
	}

	@Override
	public void read(ByteBuf buf, Direction direction, int protocolVersion) {
		this.f = buf.readByte() != 0;
	}

	@Override
	public void write(ByteBuf buf, Direction direction, int protocolVersion) {
		buf.writeByte(this.f ? 1 : 0);
	}

	public static class InLook extends InFlying {

		public InLook() {
			this.hasLook = true;
		}

		@Override
		public void read(ByteBuf buf, Direction direction, int protocolVersion) {
			this.yaw = buf.readFloat();
			this.pitch = buf.readFloat();
			super.read(buf, direction, protocolVersion);
		}

		@Override
		public void write(ByteBuf buf, Direction direction, int protocolVersion) {
			buf.writeFloat(yaw);
			buf.writeFloat(pitch);
			super.write(buf, direction, protocolVersion);
		}

	}

	public static class InPosition extends InFlying {

		public InPosition() {
			this.hasPos = true;
		}

		@Override
		public void read(ByteBuf buf, Direction direction, int protocolVersion) {
			this.x = buf.readDouble();
			this.y = buf.readDouble();
			this.z = buf.readDouble();
			super.read(buf, direction, protocolVersion);
		}

		@Override
		public void write(ByteBuf buf, Direction direction, int protocolVersion) {
			buf.writeDouble(x);
			buf.writeDouble(y);
			buf.writeDouble(z);
			super.write(buf, direction, protocolVersion);
		}

	}

	public static class InPositionLook extends InFlying {

		public InPositionLook() {
			this.hasPos = true;
			this.hasLook = true;
		}

		@Override
		public void read(ByteBuf buf, Direction direction, int protocolVersion) {
			this.x = buf.readDouble();
			this.y = buf.readDouble();
			this.z = buf.readDouble();
			this.yaw = buf.readFloat();
			this.pitch = buf.readFloat();
			super.read(buf, direction, protocolVersion);
		}

		@Override
		public void write(ByteBuf buf, Direction direction, int protocolVersion) {
			buf.writeDouble(x);
			buf.writeDouble(y);
			buf.writeDouble(z);
			buf.writeFloat(yaw);
			buf.writeFloat(pitch);
			super.write(buf, direction, protocolVersion);
		}

	}

}
