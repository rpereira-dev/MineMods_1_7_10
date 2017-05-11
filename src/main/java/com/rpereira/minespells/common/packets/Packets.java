package com.rpereira.minespells.common.packets;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class Packets {
	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel("MineSpells");

	public static void preInit() {
		network.registerMessage(PacketSpellToClient.Handler.class, PacketSpellToClient.class, 0, Side.CLIENT);
		network.registerMessage(PacketSpellToServer.Handler.class, PacketSpellToServer.class, 1, Side.SERVER);
	}
}
