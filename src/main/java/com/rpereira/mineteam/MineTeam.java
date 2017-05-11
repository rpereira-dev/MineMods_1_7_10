package com.rpereira.mineteam;

import com.rpereira.mineteam.common.MineTeamProxy;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = MineTeam.MODID, name = MineTeam.NAME, version = MineTeam.VERSION)
public class MineTeam {
	public static final String MODID = "mineteam";
	public static final String NAME = "MineTeam";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = "com.rpereira.mineteam.client.MineTeamProxyClient", serverSide = "com.rpereira.mineteam.common.MineTeamProxy")
	public static MineTeamProxy proxy;

	@EventHandler
	public void preInit(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "PreInit");
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "Init");
		proxy.init();
	}
}
