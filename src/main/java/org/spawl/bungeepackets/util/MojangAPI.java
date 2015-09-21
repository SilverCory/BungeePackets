package org.spawl.bungeepackets.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.UUID;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.spawl.bungeepackets.lib.org.json.simple.JSONObject;
import org.spawl.bungeepackets.lib.org.json.simple.parser.JSONParser;

public class MojangAPI {

	private static HashMap<UUID, JSONObject> skinMap = new HashMap<UUID, JSONObject>();
	private static HashMap<String, SimpleEntry<UUID, String>> infoMap = new HashMap<String, SimpleEntry<UUID, String>>();

	public static JSONObject getRawSkin(UUID id) {
		if(skinMap.containsKey(id))
			return skinMap.get(id);
		
		try{
			HttpURLConnection connection = (HttpURLConnection)setupConnection(new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + id.toString().replace("-", "") + "?unsigned=false"));

			if (connection.getResponseCode() == 429) {
				return null;
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String result = in.readLine();
			in.close();

			JSONObject obj = (JSONObject)new JSONParser().parse(result);		
			skinMap.put(id, obj);
			return obj;
		}catch(Exception e){
			return null;
		}
	}

	public static SimpleEntry<UUID, String> requestPlayerInfo(String value) {
		if(infoMap.containsKey(value))
			return infoMap.get(value);
		
		SimpleEntry<UUID, String> entry = null;
		if(value.length() > 16) {
			UUID id = UUID.fromString(value);
			ProxiedPlayer p = ProxyServer.getInstance().getPlayer(id);
			if(p != null)
			{
				entry = new SimpleEntry<UUID, String>(p.getUniqueId(), p.getName());
			}
		}else{
			ProxiedPlayer p = ProxyServer.getInstance().getPlayer(value);
			if(p != null)
				entry = new SimpleEntry<UUID, String>(p.getUniqueId(), p.getName());
		}
		
		if(entry != null) {
			infoMap.put(value, entry);
			return entry;
		}
		
		try{
			URL url = new URL("http://mcuuid.com/api/"+value);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String raw = in.readLine();
			
			JSONObject object = (JSONObject) new JSONParser().parse(raw);
			if(object.containsKey("uuid_formatted")) {
				UUID id = UUID.fromString(object.getString("uuid_formatted"));
				String name = object.getString("name");
				entry = new SimpleEntry<UUID, String>(id, name);
				infoMap.put(value, entry);
				return entry;
			}else{
				return null;
			}
		}catch(Exception e) {
			return null;
		}
	}	

	private static URLConnection setupConnection(URL url) throws IOException {
		URLConnection connection = url.openConnection();
		connection.setConnectTimeout(10000);
		connection.setReadTimeout(10000);
		connection.setUseCaches(false);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		return connection;
	}

}
