# BungeePackets
An in depth packet library for BungeeCord. This library allows developers to create plugins that allow for inventories across server networks, play sounds, particle effects, and soon to be lots more!

### Table of Contents
[How does it work?](https://github.com/Spawl/BungeePackets/blob/master/README.md#how-does-it-work)
[Usage](https://github.com/Spawl/BungeePackets/blob/master/README.md#usage)
[Inventories](https://github.com/Spawl/BungeePackets/blob/master/README.md#inventories)
[Customization](https://github.com/Spawl/BungeePackets/blob/master/README.md#customization)

## How does it work?
Basically, every time a player connects to a server, a new server connection is made between the BungeeCord and the server. The packets then get piped to the player through this. BungeePackets simply adds a little filter in the pipe. This way we can detect any and all packets coming in and out of the Bungee.
## Usage
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
With the code above, we will be able to track the players location very easily.
## Inventories
The inventory API is much like Bukkit. Basically you create an inventory object. The inventory object takes two parameters, a string and an integer. The string is the inventory title, and the integer is the amount of slots (must be divisable by 9). In the example below, we are creating an inventory, adding a few items to it, and then opening it for the player. 
```
ProxiedPlayer p = null; /* Make this not null when you do it ;) */
Inventory inventory = new Inventory("Test!", 54);
inventory.setItem(0, new ItemStack(Material.APPLE));
inventory.open(p);
```
This will open an inventory for the player. Items cannot be taken out of these inventories and are NOT "real items".
## Customization
With this library you can register your own packets! This is just in-case I forget to add one that you need. All of the packets that are in normal minecraft have not been added yet. But soon they will be. To add your own packet, extend the org.spawl.bungeepackets.Packet class. This is an abstract class so you will get 3 methods to start with. Read, write, and handle. Read is called when the BungeeCord recieves the packet, write is called when the Bungee is writing the packet to a pipeline, and handle is called after it is read. Below is an example of how you can register your own packet!
```
BungeePackets.registerPacket(Protocol.DirectionData.TO_SERVER, <ID>, <CLASS EXTENDING PACKET>);
```
