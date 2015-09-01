package org.spawl.bungeepackets.skin;

import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.UUID;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.connection.LoginResult;
import net.md_5.bungee.connection.LoginResult.Property;

import org.spawl.bungeepackets.lib.org.json.simple.JSONObject;
import org.spawl.bungeepackets.nbt.NBTTagCompound;
import org.spawl.bungeepackets.nbt.NBTTagList;
import org.spawl.bungeepackets.util.MojangAPI;
import org.spawl.bungeepackets.util.Reflection;

public class GameProfile {

	private UUID id;
	private String name;
	private PropertyMap map;


	public GameProfile(UUID id, String name) {
		this.id = id;
		this.name = name;
		this.map = new PropertyMap();
	}

	public boolean loadSkin() {
		SimpleEntry<UUID, String> entry = new SimpleEntry<UUID, String>(id, name);
		if(this.id == null) {
			if(this.name != null) {
				entry = MojangAPI.requestPlayerInfo(this.name);
			}else{
				return false;
			}
		}else{
			if(this.name == null) {
				entry = MojangAPI.requestPlayerInfo(this.id.toString());
			}
		}
		
		this.name = entry.getValue();
		this.id = entry.getKey();

		if(this.id == null || this.name == null)
			return false;

		try{
			boolean done = false;
			ProxiedPlayer p = ProxyServer.getInstance().getPlayer(id);
			if(p != null) {
				if(p.getPendingConnection() != null) {
					if(p.getPendingConnection() instanceof InitialHandler) {
						InitialHandler handler = (InitialHandler) p.getPendingConnection();
						LoginResult result = (LoginResult) Reflection.get(handler, "loginProfile");
						for(Property property : result.getProperties()) {
							this.map.put(result.getId(), property);
						}
						done = true;
					}
				}
			}
			Skin skin = null;
			
			if(!done) {
				JSONObject object = MojangAPI.getRawSkin(this.id);
				skin = new Skin(object);
			}else{
				skin = new Skin(this.map);
			}

			skin.apply(this);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public UUID getUUID(){
		return id;
	}

	public String getName() {
		return name;
	}

	public PropertyMap getProperties() {
		return this.map;
	}

	public void setUUID(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperties(PropertyMap map) {
		this.map = map;
	}

	public NBTTagCompound serialize(NBTTagCompound compound) {
		if(this.getName() != null)
			compound.setString("Name", this.getName());
		if(this.getUUID() != null)
			compound.setString("Id", this.getUUID().toString());
		if(!this.map.isEmpty()) {
			NBTTagCompound comp = new NBTTagCompound();
			Iterator<String> iterator = this.map.keySet().iterator();
			while(iterator.hasNext()) {
				String s = iterator.next();
				NBTTagList list = new NBTTagList();
				NBTTagCompound comp2;

				for(Property p : this.map.get(s)) {
					comp2 = new NBTTagCompound();
					comp2.setString("Value", p.getValue());
					if(p.getSignature() != null) {
						comp2.setString("Signature", p.getSignature());
					}
					list.add(comp2);
				}
				comp.set(s, list);
			}
			compound.set("Properties", comp);
		}
		return compound;
	}

}
