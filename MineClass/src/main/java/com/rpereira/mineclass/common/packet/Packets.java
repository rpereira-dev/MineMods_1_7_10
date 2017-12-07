package com.rpereira.mineclass.common.packet;

import com.rpereira.minespells.MineSpells;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class Packets {

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(MineSpells.MODID);
	private static int ID = 0;

	private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(
			Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
		network.registerMessage(messageHandler, requestMessageType, ID++, side);
	}

	public static void preInit() {
		Logger.get().log(Logger.Level.FINE, "preInit Packets");
		registerPacket(PacketChangeClass.HandlerClient.class, PacketChangeClass.class, Side.CLIENT);
		registerPacket(PacketChangeClass.HandlerServer.class, PacketChangeClass.class, Side.SERVER);

		registerPacket(PacketEntityClassInstance.HandlerClient.class, PacketEntityClassInstance.class, Side.CLIENT);

	}
}
