# BungeePackets
==========
An in depth packet library for BungeeCord. This library allows developers to create plugins that allow for inventories across server networks, play sounds, particle effects, and soon to be lots more!
Usage
-------
BungeePackets adds in one simple event to manage packets. PacketEvent. The packet event is used to manage all incoming and outcoming packets. With every incoming and outcoming packet there is a player assigned to it somewhere in the pipe. Using this you can differentiate wheather a server is sending a packet to BungeeCord, vice-versa.

In the example below, we are listening for an incoming packet coming from the client to the BungeeCord. We can check who is sending/recieving packets by checking if they are an instance of another connection. To check if the sender is a player, check if the sender is an instanceof a **ProxiedPlayer**. To check if the sender is Bungee, check if the sender is an instance of a **BungeeConnection**. To check if the sender is a server, check if the sender is an instanceof a **ServerConnection**.
```
@EventHandler
public void onPacket(PacketEvent event) {
	if(event.getSender() instanceof ProxiedPlayer && event.getReciever() instanceof BungeeConnection) {
		if(event.getPacket() instanceof InFlying) {
			InFlying packet = (InFlying) event.getPacket();
		}
	}
}
```
