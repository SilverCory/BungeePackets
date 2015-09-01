package org.spawl.bungeepackets.connection;

import java.net.InetSocketAddress;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.Connection;

public class BungeeConnection implements Connection {

	@Override
	public void disconnect(String arg0) {
	}

	@Override
	public void disconnect(BaseComponent... arg0) {
	}

	@Override
	public void disconnect(BaseComponent arg0) {
	}

	@Override
	public InetSocketAddress getAddress() {
		return null;
	}

	@Override
	public Unsafe unsafe() {
		return null;
	}

}
