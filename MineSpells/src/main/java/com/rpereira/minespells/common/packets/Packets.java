package com.rpereira.minespells.common.packets;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class Packets {
	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel("MineSpells");

	public static void preInit() {
		network.registerMessage(PacketSpell.HandlerClient.class, PacketSpell.class, 0, Side.CLIENT);
		network.registerMessage(PacketSpell.HandlerServer.class, PacketSpell.class, 1, Side.SERVER);
	}
}
