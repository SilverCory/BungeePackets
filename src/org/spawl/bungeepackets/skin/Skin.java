package org.spawl.bungeepackets.skin;

import net.md_5.bungee.connection.LoginResult.Property;

import org.spawl.bungeepackets.lib.org.json.simple.JSONArray;
import org.spawl.bungeepackets.lib.org.json.simple.JSONObject;

public class Skin {

	private String name;
	private String value;
	private String signature;
	
	public Skin(PropertyMap map) {
		for(Property p : map.values()) {
			this.name = p.getName();
			this.value = p.getValue();
			this.signature = p.getSignature();
		}
	}

	public Skin(JSONObject object) throws Exception {
		JSONArray properties = (JSONArray) object.get("properties");
		for(int i = 0; i < properties.size(); i++) {
			JSONObject property = (JSONObject) properties.get(i);
			this.name = property.getString("name");
			this.value = property.getString("value");
			this.signature = property.getString("signature");
		}
	}

	public void apply(GameProfile profile) {
		profile.getProperties().put(name, new Property(this.name, this.value, this.signature));
	}

}
