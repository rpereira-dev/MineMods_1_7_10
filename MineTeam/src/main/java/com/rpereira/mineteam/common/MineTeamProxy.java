package com.rpereira.mineteam.common;

import com.rpereira.mineteam.common.packets.PacketServer;
import com.rpereira.mineteam.common.packets.Packets;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class MineTeamProxy {

	private PacketServer packetServer;

	public void preInit() {
		Logger.get().log(Logger.Level.FINE, "PreInit MineTeamProxy");
		Packets.preInit();
	}

	public void init() {
		Logger.get().log(Logger.Level.FINE, "Init MineTeamProxy");
		this.packetServer = new PacketServer();
		FMLCommonHandler.instance().bus().register(this);
	}

	@SubscribeEvent
	public void onServerTick(ServerTickEvent event) {
		this.packetServer.onServerTick();
	}
}
