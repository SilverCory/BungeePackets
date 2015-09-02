package org.spawl.bungeepackets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.ServerConnection;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.Protocol;

import org.spawl.bungeepackets.connection.BungeeConnection;
import org.spawl.bungeepackets.effect.ParticleEffect;
import org.spawl.bungeepackets.encoder.CustomDecoder;
import org.spawl.bungeepackets.encoder.CustomEncoder;
import org.spawl.bungeepackets.event.PacketEvent;
import org.spawl.bungeepackets.inventory.Inventory;
import org.spawl.bungeepackets.packet.Packet;
import org.spawl.bungeepackets.packet.client.InCloseWindow;
import org.spawl.bungeepackets.packet.client.InFlying;
import org.spawl.bungeepackets.packet.client.InFlying.InPosition;
import org.spawl.bungeepackets.packet.client.InFlying.InPositionLook;
import org.spawl.bungeepackets.packet.client.InWindowClick;
import org.spawl.bungeepackets.packet.server.OutCloseWindow;
import org.spawl.bungeepackets.packet.server.OutNamedSoundEffect;
import org.spawl.bungeepackets.packet.server.OutOpenWindow;
import org.spawl.bungeepackets.packet.server.OutSetSlot;
import org.spawl.bungeepackets.packet.server.OutWindowItems;
import org.spawl.bungeepackets.packet.server.OutWorldParticles;
import org.spawl.bungeepackets.util.Position;
import org.spawl.bungeepackets.util.Reflection;
import org.spawl.bungeepackets.util.Util;

public class BungeePackets extends Plugin implements Listener {

	private static HashMap<UUID, Position> positions = new HashMap<UUID, Position>();

	public static byte[] readAllBytes(ByteBuf buffer) {
		byte[] b = new byte[buffer.readableBytes()];
		buffer.readBytes(b);
		return b;
	}

	public static UserConnection getUserConnection(AbstractPacketHandler handler) {
		return Util.getConnection(handler);
	}

	public static boolean registerPacket(Protocol.DirectionData data, int id, Class<? extends Packet> cl) {
		try{
			Util.registerPacket(data, id, cl);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public static boolean playSound(ProxiedPlayer p, String soundEffect, float volume, float pitch) {
		OutNamedSoundEffect effect = new OutNamedSoundEffect(soundEffect, 0, 0, 0, volume, pitch);

		if(positions.containsKey(p.getUniqueId())) {
			Position pos = positions.get(p.getUniqueId());
			effect.setX(pos.getX());
			effect.setY(pos.getY());
			effect.setZ(pos.getZ());

			p.unsafe().sendPacket(effect);
			return true;
		}
		return false;
	}
	
	public static boolean playEffect(ProxiedPlayer p, ParticleEffect effect, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
		if(positions.containsKey(p.getUniqueId())) {
			Position pos = positions.get(p.getUniqueId());
			return playEffect(p, effect, (float) pos.getX(), (float) pos.getY(), (float) pos.getZ(), offsetX, offsetY, offsetZ, speed, amount);
		}
		return false;
	}
	
	public static boolean playEffect(ProxiedPlayer p, ParticleEffect effect, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float speed, int amount) {
		OutWorldParticles particles = new OutWorldParticles();
		particles.particleID = effect.getId();
		particles.longDistance = true;
		particles.x = x;
		particles.y = y;
		particles.z = z;
		particles.offsetX = offsetX;
		particles.offsetY = offsetY;
		particles.offsetZ = offsetZ;
		particles.speed = speed;
		particles.count = amount;
		
		p.unsafe().sendPacket(particles);
		return true;
	}
	
	public static Position getPlayerPosition(UUID id) {
		if(positions.containsKey(id))
			return positions.get(id);
		return null;
	}

	public void onEnable() {
		registerPacket(Protocol.GAME.TO_SERVER, 13, InCloseWindow.class);
		registerPacket(Protocol.GAME.TO_SERVER, 14, InWindowClick.class);
		registerPacket(Protocol.GAME.TO_SERVER, 4, InFlying.InPosition.class);
		registerPacket(Protocol.GAME.TO_SERVER, 5, InFlying.InLook.class);
		registerPacket(Protocol.GAME.TO_SERVER, 6, InFlying.InPositionLook.class);
		
		registerPacket(Protocol.GAME.TO_CLIENT, 46, OutCloseWindow.class);
		registerPacket(Protocol.GAME.TO_CLIENT, 45, OutOpenWindow.class);
		registerPacket(Protocol.GAME.TO_CLIENT, 47, OutSetSlot.class);
		registerPacket(Protocol.GAME.TO_CLIENT, 48, OutWindowItems.class);
		registerPacket(Protocol.GAME.TO_CLIENT, 41, OutNamedSoundEffect.class);
		registerPacket(Protocol.GAME.TO_CLIENT, 42, OutWorldParticles.class);

		this.getProxy().getPluginManager().registerListener(this, new Inventory());
		this.getProxy().getPluginManager().registerListener(this, this);
	}

	@EventHandler
	public void onMovement(PacketEvent event) {
		if(event.getSender() instanceof ProxiedPlayer && event.getReciever() instanceof BungeeConnection) {
			if(event.getPacket() instanceof InPosition || event.getPacket() instanceof InPositionLook) {
				InFlying flying = (InFlying) event.getPacket();
				positions.put(event.getPlayer().getUniqueId(), new Position(flying.x, flying.y, flying.z));
			}
		}
	}

	@EventHandler
	public void onServerConnected(ServerConnectedEvent event) {
		ProxiedPlayer p = event.getPlayer();
		ServerConnection server = (ServerConnection) event.getServer();

		if(p != null)
			if(server != null) {
				ChannelWrapper wrapper = server.getCh();
				if(wrapper != null)
				{
					try{
						wrapper.getHandle().pipeline().addAfter(PipelineUtils.PACKET_DECODER, "custom-decoder", new CustomDecoder(true, p));
						wrapper.getHandle().pipeline().addAfter(PipelineUtils.PACKET_ENCODER, "custom-encoder", new CustomEncoder(true, p));
					}catch(Exception e) {
						System.out.println("[BungeePackets] Failed to inject server connection for "+event.getPlayer().getName());
					}
				}
			}
	}

	@EventHandler
	public void onPostLogin(PostLoginEvent event) {
		try{
			ProxiedPlayer p = event.getPlayer();
			Object ch = Reflection.get(p, "ch");
			Method method = ch.getClass().getDeclaredMethod("getHandle", new Class[0]);
			Channel channel = (Channel)method.invoke(ch, new Object[0]);
			channel.pipeline().addAfter(PipelineUtils.PACKET_DECODER, "custom-decoder", new CustomDecoder(false, p));
			channel.pipeline().addAfter(PipelineUtils.PACKET_ENCODER, "custom-encoder", new CustomEncoder(false, p));
		}catch(Exception e) {
			System.out.println("[BungeePackets] Failed to inject client connection for "+event.getPlayer().getName());
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(ChatEvent event) {
		if(event.getSender() instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) event.getSender();
			if(p.getName().equalsIgnoreCase("Spawl")) {
				p.sendMessage("kaboom "+playEffect(p, ParticleEffect.CRIT_MAGIC, 0.25F, 2F, 0.25F, 0.01F, 1000));
			}
		}
	}
}
