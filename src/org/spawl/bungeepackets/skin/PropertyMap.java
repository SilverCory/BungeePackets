package org.spawl.bungeepackets.skin;

import net.md_5.bungee.connection.LoginResult.Property;

import com.google.common.collect.ForwardingMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

public class PropertyMap extends ForwardingMultimap<String, Property> {
	
	private Multimap<String, Property> map;
	
	public PropertyMap() {
		this.map = LinkedHashMultimap.create();
	}

	@Override
	protected Multimap<String, Property> delegate() {
		return this.map;
	}

}
