package com.rpereira.mineteam.common.packets;

import com.rpereira.mineteam.MineTeam;
import com.rpereira.mineteam.common.packets.client.PacketTeamInvitation;
import com.rpereira.mineteam.common.packets.server.PacketTeamAcceptInvitation;
import com.rpereira.mineteam.common.packets.server.PacketTeamCreateTeam;
import com.rpereira.mineteam.common.packets.server.PacketTeamInvitePlayer;
import com.rpereira.mineteam.common.packets.server.PacketTeamLeave;
import com.rpereira.mineteam.common.packets.server.PacketTeamRefuseInvitation;
import com.rpereira.mineteam.common.packets.server.PacketTeamRename;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class Packets {

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(MineTeam.MODID);
	private static int ID = 0;

	private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(
			Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
		network.registerMessage(messageHandler, requestMessageType, ID, side);
		++ID;
	}

	public static void preInit() {
		Logger.get().log(Logger.Level.FINE, "preInit Packets");

		// the side for the handler
		registerPacket(PacketTeamCreateTeam.Handler.class, PacketTeamCreateTeam.class, Side.SERVER);
		registerPacket(PacketTeamInvitePlayer.Handler.class, PacketTeamInvitePlayer.class, Side.SERVER);
		registerPacket(PacketTeamLeave.Handler.class, PacketTeamLeave.class, Side.SERVER);
		registerPacket(PacketTeamRefuseInvitation.Handler.class, PacketTeamRefuseInvitation.class, Side.SERVER);
		registerPacket(PacketTeamAcceptInvitation.Handler.class, PacketTeamAcceptInvitation.class, Side.SERVER);
		registerPacket(PacketTeamRename.Handler.class, PacketTeamRename.class, Side.SERVER);

		// the side for the handler
		registerPacket(PacketTeamInvitation.Handler.class, PacketTeamInvitation.class, Side.CLIENT);
	}
}
