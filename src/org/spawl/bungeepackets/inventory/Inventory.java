package org.spawl.bungeepackets.inventory;

import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.ServerConnection;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import org.spawl.bungeepackets.connection.BungeeConnection;
import org.spawl.bungeepackets.event.PacketEvent;
import org.spawl.bungeepackets.item.ItemStack;
import org.spawl.bungeepackets.packet.client.InCloseWindow;
import org.spawl.bungeepackets.packet.client.InWindowClick;
import org.spawl.bungeepackets.packet.server.OutOpenWindow;
import org.spawl.bungeepackets.packet.server.OutSetSlot;
import org.spawl.bungeepackets.packet.server.OutWindowItems;

public class Inventory implements Listener {

	private static HashMap<UUID, Integer> open = new HashMap<UUID, Integer>();
	private static HashMap<Integer, Inventory> inventories = new HashMap<Integer, Inventory>();
	private static HashMap<UUID, PlayerInventory> playerInventories = new HashMap<UUID, PlayerInventory>();

	private static int currentInventoryId = 60;

	public static Inventory getInventoryById(int id) {
		if(inventories.containsKey(id))
			return inventories.get(id);
		return null;
	}

	public static Inventory getOpenInventory(UUID id) {
		if(open.containsKey(id)) {
			int i = open.get(id);
			Inventory inv = getInventoryById(i);
			return inv;
		}
		return null;
	}

	public static PlayerInventory getPlayerInventory(UUID id) {
		if(playerInventories.containsKey(id)) {
			return playerInventories.get(id);
		}
		PlayerInventory inv = new PlayerInventory(id);
		playerInventories.put(id, inv);
		return inv;
	}

	@EventHandler
	public void onPacket(PacketEvent event) {
		if(event.getSender() instanceof ServerConnection && event.getReciever() instanceof BungeeConnection) {
			if(event.getPacket() instanceof OutWindowItems) {
				OutWindowItems items = (OutWindowItems) event.getPacket();
				if(items.id == 0) {
					PlayerInventory inv = getPlayerInventory(event.getPlayer().getUniqueId());
					inv.setItems(items.items);
					playerInventories.put(event.getPlayer().getUniqueId(), inv);
				}
			}
			if(event.getPacket() instanceof OutSetSlot) {
				OutSetSlot slot = (OutSetSlot) event.getPacket();
				if(slot.windowID == 0) {
					PlayerInventory inv = getPlayerInventory(event.getPlayer().getUniqueId());
					inv.setItem(slot.slot, slot.item);
					playerInventories.put(event.getPlayer().getUniqueId(), inv);
				}
			}
		}

		if(event.getSender() instanceof ProxiedPlayer && event.getReciever() instanceof BungeeConnection) {
			if(event.getPacket() instanceof InCloseWindow) {
				InCloseWindow packet = (InCloseWindow) event.getPacket();
				if(open.containsKey(event.getPlayer().getUniqueId())) {
					if(open.get(event.getPlayer().getUniqueId()) == packet.windowID) {
						event.setCancelled(true);
					}
					open.remove(event.getPlayer().getUniqueId());
				}
			}

			if(event.getPacket() instanceof InWindowClick) {
				InWindowClick packet = (InWindowClick) event.getPacket();
				if(open.containsKey(event.getPlayer().getUniqueId())) {
					if(open.get(event.getPlayer().getUniqueId()) == packet.windowID) {
						event.setCancelled(true);

						if(inventories.containsKey(packet.windowID)) {
							Inventory i = inventories.get(packet.windowID);
							i.handleClick(event.getPlayer(), packet.item, packet.slot, packet.shift == 1 ? true : false, packet.button == 0 ? true : false);
						}
					}else{
						open.remove(event.getPlayer().getUniqueId());
					}
				}
			}
		}
	}

	private HashMap<Integer, ItemStack> map;
	private HashMap<Integer, ClickHandler> handler;
	private String title;
	private int slots;
	private int inventoryID;

	public Inventory(){
		this("Chest", 27);
	}

	public Inventory(String title, int slots) {
		this.map = new HashMap<Integer, ItemStack>();
		this.handler = new HashMap<Integer, ClickHandler>();
		this.title = title;
		this.slots = slots;
		this.inventoryID = currentInventoryId;

		inventories.put(inventoryID, this);
	}

	public int getInventoryId() {
		return inventoryID;
	}

	public int getSlots() {
		return slots;
	}

	public String getTitle() {
		return title;
	}

	public ItemStack getItem(int slot) {
		if(map.containsKey(slot))
			return map.get(slot);
		return null;
	}

	public boolean setItem(int slot, ItemStack stack) {
		return setItem(slot, stack, null);
	}
	
	public boolean setItem(int slot, ItemStack stack, ClickHandler handler) {
		map.put(slot, stack);

		for(UUID id : open.keySet()) {
			if(open.get(id) == inventoryID) {
				ProxiedPlayer p = ProxyServer.getInstance().getPlayer(id);
				if(p != null)
					updateSlot(p, slot);
			}
		}
		
		this.handler.put(slot, handler);
		return true;
	}

	public boolean addItem(ItemStack stack) {
		int slot = 0;
		while(map.containsKey(slot)) {
			slot += 1;
		}

		if(slot > slots - 1)
			return false;

		return setItem(slot, stack);
	}

	public boolean open(ProxiedPlayer p) {
		open.put(p.getUniqueId(), inventoryID);

		OutOpenWindow window = new OutOpenWindow();
		window.id = inventoryID;
		window.title = "{\"text\":\""+title+"\"}";
		window.slots = slots;
		window.windowType = "Chest";

		p.unsafe().sendPacket(window);

		for(int i : map.keySet()) {
			OutSetSlot slot = new OutSetSlot();
			slot.windowID = inventoryID;
			slot.slot = i;
			slot.item = map.get(i);

			if(slot.item != null)
			{
				p.unsafe().sendPacket(slot);
			}
		}

		return true;
	}

	private void handleClick(ProxiedPlayer p, ItemStack stack, int slot, boolean shift, boolean leftClick) {
		PlayerInventory pinv = getPlayerInventory(p.getUniqueId());
		
		for(int i = 0; i < slots; i++)
			if(map.containsKey(i))
			{
				OutSetSlot slotPacket = new OutSetSlot();
				slotPacket.windowID = inventoryID;
				slotPacket.slot = i;
				slotPacket.item = map.get(i);
				
				p.unsafe().sendPacket(slotPacket);
			}else{
				OutSetSlot slotPacket = new OutSetSlot();
				slotPacket.windowID = inventoryID;
				slotPacket.slot = i;
				slotPacket.item = null;
				
				p.unsafe().sendPacket(slotPacket);
			}
		
		OutWindowItems pWindow = new OutWindowItems();
		pWindow.id = 0;
		pWindow.items = pinv.getItems();
		
		p.unsafe().sendPacket(pWindow);

		OutSetSlot nullifyPacket = new OutSetSlot();
		nullifyPacket.windowID = -1;
		nullifyPacket.slot = -1;

		p.unsafe().sendPacket(nullifyPacket);
		
		if(handler.containsKey(slot)) {
			ClickHandler handle = handler.get(slot);
			if(handle != null)
				handle.onClick(p, slot, stack, !leftClick, shift);
		}
	}

	private void updateSlot(ProxiedPlayer p, int slot) {
		OutSetSlot packet = new OutSetSlot();
		packet.windowID = inventoryID;
		packet.slot = slot;
		packet.item = (map.containsKey(slot) ? map.get(slot) : null);

		p.unsafe().sendPacket(packet);
	}
	
	public interface ClickHandler {
		public void onClick(ProxiedPlayer p, int slot, ItemStack clicked, boolean rightClick, boolean shiftClick);
	}

}
