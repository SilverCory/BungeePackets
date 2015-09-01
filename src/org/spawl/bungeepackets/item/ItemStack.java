package org.spawl.bungeepackets.item;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.DataOutput;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.spawl.bungeepackets.nbt.NBTBase;
import org.spawl.bungeepackets.nbt.NBTCompressedStreamTools;
import org.spawl.bungeepackets.nbt.NBTReadLimiter;
import org.spawl.bungeepackets.nbt.NBTTagCompound;
import org.spawl.bungeepackets.nbt.NBTTagList;
import org.spawl.bungeepackets.nbt.NBTTagString;
import org.spawl.bungeepackets.util.MojangAPI;

public class ItemStack {

	static final ItemMetaKey NAME = new ItemMetaKey("Name", "display-name");
	static final ItemMetaKey DISPLAY = new ItemMetaKey("display");
	static final ItemMetaKey LORE = new ItemMetaKey("Lore", "lore");
	static final ItemMetaKey ENCHANTMENTS = new ItemMetaKey("ench", "enchants");
	static final ItemMetaKey ENCHANTMENTS_ID = new ItemMetaKey("id");
	static final ItemMetaKey ENCHANTMENTS_LVL = new ItemMetaKey("lvl");
	static final ItemMetaKey HIDEFLAGS = new ItemMetaKey("HideFlags", "ItemFlags");
	static final ItemMetaKey UNBREAKABLE = new ItemMetaKey("Unbreakable");
	static final ItemMetaKey SKULL_OWNER = new ItemMetaKey("SkullOwner", "skull-owner");

	private Material type;
	private int amount;
	private int data;
	private NBTTagCompound tag;

	public ItemStack(Material type) {
		this.type = type;
		this.amount = 1;
		this.data = 0;
		this.tag = new NBTTagCompound();
	}
	
	public ItemStack(ByteBuf buf) {
		short id = buf.readShort();
		
		if(id >= 0) {
			int amount = buf.readByte();
			int data = buf.readShort();
			
			this.type = Material.getMaterial(id);
			
			if(this.type == null)
				System.out.println("Null type for id: "+id);
			
			this.amount = amount;
			this.data = data;
			
			int i = buf.readerIndex();
			byte b = buf.readByte();
			
			if(b == 0) {
				return;
			}
			
			buf.readerIndex(i);
			NBTTagCompound compound = NBTCompressedStreamTools.a(new ByteBufInputStream(buf), new NBTReadLimiter(2097152L));
			this.tag = compound;
		}
	}
	
	public void write(ByteBuf buf) {
		if(type == null)
			buf.writeShort(-1);
		else {
			buf.writeShort(type.getId());
			buf.writeByte(amount);
			buf.writeShort(data);
			
			if(tag == null || tag.isEmpty())
				buf.writeByte(0);
			else {
				NBTCompressedStreamTools.a(tag, (DataOutput) new ByteBufOutputStream(buf));
			}
		}
	}
	
	public Material getType() {
		return type;
	}

	public ItemStack setType(Material type) {
		this.type = type;
		return this;
	}

	public ItemStack setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public ItemStack setData(int data) {
		this.data = data;
		return this;
	}

	public ItemStack setTitle(String title) {
		setDisplayTag(NAME.NBT, new NBTTagString(title));
		return this;
	}
	
	public ItemStack setOwner(UUID id) {
		try {
			MojangAPI.applySkinProfile(id.toString(), this.tag);
			System.out.println("Successfully applied skin ("+id+")!");
		} catch (Exception e) {
			System.out.println("Failed to grab skin ("+id+"): "+e.getMessage());
		}
		return this;
	}
	
	public ItemStack setLore(List<String> lore) {
		setDisplayTag(LORE.NBT, createStringList(lore));
		return this;
	}
	
	public ItemStack setLore(String... lore) {
		setDisplayTag(LORE.NBT, createStringList(Arrays.asList(lore)));
		return this;
	}
	
	public ItemStack addFakeGlow() {
		return addEnchantment(Enchantment.FAKE_GLOW, 1);
	}
	
	public ItemStack addEnchantment(Enchantment enchant, int level) {
		NBTTagList list = tag.getList(ENCHANTMENTS.NBT);
		
		if(list == null)
			list = new NBTTagList();
		
		NBTTagCompound subtag = new NBTTagCompound();
		subtag.setShort(ENCHANTMENTS_ID.NBT, (short) enchant.getId());
		subtag.setShort(ENCHANTMENTS_LVL.NBT, (short) level);
		
		list.add(subtag);
		
		tag.set(ENCHANTMENTS.NBT, list);
		
		return this;
	}
	
	private NBTTagList createStringList(List<String> list) {
		if(list == null || list.isEmpty())
			return null;
		
		NBTTagList nbt = new NBTTagList();
		for(String s : list)
			nbt.add(new NBTTagString(s));
		return nbt;
	}

	private void setDisplayTag(String key, NBTBase value) {
		NBTTagCompound display = tag.getCompound(DISPLAY.NBT);

		if(display == null)
			display = new NBTTagCompound();

		if(!tag.hasKey(DISPLAY.NBT)) {
			tag.set(DISPLAY.NBT, display);
		}

		display.set(key, value);
	}

	static class ItemMetaKey
	{
		final String BUKKIT;
		final String NBT;

		ItemMetaKey(String both)
		{
			this(both, both);
		}

		ItemMetaKey(String nbt, String bukkit) {
			this.NBT = nbt;
			this.BUKKIT = bukkit;
		}

		static @interface Specific
		{
			public abstract To value();

			public static enum To
			{
				BUKKIT, 
				NBT;
			}
		}
	}
}
