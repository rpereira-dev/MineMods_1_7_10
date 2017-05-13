package com.rpereira.mineexp.common.packet;

import com.rpereira.mineexp.MineExp;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class Packets {

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(MineExp.MODID);
	private static int ID = 0;

	private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(
			Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
		network.registerMessage(messageHandler, requestMessageType, ID, side);
		++ID;
	}

	public static void preInit() {
		Logger.get().log(Logger.Level.FINE, "preInit Packets");

		// the side for the handler
		// registerPacket(PacketTeamCreateTeam.Handler.class,
		// PacketTeamCreateTeam.class, Side.SERVER);

		// the side for the handler
		registerPacket(PacketExpBarInstanceCreate.Handler.class, PacketExpBarInstanceCreate.class, Side.CLIENT);
		registerPacket(PacketExpBarInstanceRemove.Handler.class, PacketExpBarInstanceRemove.class, Side.CLIENT);
		registerPacket(PacketExpBarUpdate.Handler.class, PacketExpBarUpdate.class, Side.CLIENT);
	}
}
