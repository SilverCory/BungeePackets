package org.spawl.bungeepackets.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.spawl.bungeepackets.lib.org.json.simple.JSONArray;
import org.spawl.bungeepackets.lib.org.json.simple.JSONObject;
import org.spawl.bungeepackets.lib.org.json.simple.parser.JSONParser;
import org.spawl.bungeepackets.nbt.NBTTagCompound;
import org.spawl.bungeepackets.nbt.NBTTagList;

public class MojangAPI {

	public static NBTTagCompound applySkinProfile(String id, NBTTagCompound compound) throws Exception {
		HttpURLConnection connection = (HttpURLConnection)setupConnection(new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + id.replace("-", "") + "?unsigned=false"));

		if (connection.getResponseCode() == 429) {
			return null;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String result = in.readLine();
		in.close();
		
		
		compound.setString("Id", id);
		
		JSONObject obj = (JSONObject)new JSONParser().parse(result);
		JSONArray properties = (JSONArray)obj.get("properties");
		
		compound.setString("Name", obj.getString("name"));
		
		if(!properties.isEmpty()) {
			NBTTagCompound comperties = new NBTTagCompound();
			for(Object rawProperty : properties) {
				JSONObject property = (JSONObject) rawProperty;
				
				NBTTagList list = new NBTTagList();
				NBTTagCompound compound2 = new NBTTagCompound();
				
				String value = (String)property.get("value");
				String signature = (String)property.get("signature");
				
				compound2.setString("Value", value);
				compound2.setString("Signature", signature);
				
				list.add(compound2);
				
				String name = (String)property.get("name");
				
				comperties.set(name, list);
			}
			
			compound.set("Properties", compound);
		}
		
		return compound;
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
