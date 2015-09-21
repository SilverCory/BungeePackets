package org.spawl.bungeepackets.inventory;

import java.util.UUID;

import org.spawl.bungeepackets.item.ItemStack;

public class PlayerInventory {
	
	private UUID owner;
	private ItemStack[] array;
	
	public PlayerInventory(UUID owner) {
		this.owner = owner;
		this.array = new ItemStack[45];
	}
	
	public void setItems(ItemStack[] array) {
		this.array = array;
	}
	
	public void setItem(int slot, ItemStack item) {
		array[slot] = item;
	}
	
	public UUID getOwner() {
		return owner;
	}
	
	public ItemStack[] getItems() {
		return array;
	}
	
	public ItemStack getItem(int slot) {
		return array[slot];
	}

}
