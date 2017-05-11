package com.rpereira.mineexpbar;

import com.rpereira.mineexpbar.common.MineExpBarProxy;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = MineExpBar.MODID, name = MineExpBar.NAME, version = MineExpBar.VERSION)
public class MineExpBar {
	public static final String MODID = "mineexpbar";
	public static final String NAME = "MineExpBar";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = "com.rpereira.mineexpbar.client.MineExpBarProxyClient", serverSide = "com.rpereira.mineexpbar.common.MineExpBarProxy")
	public static MineExpBarProxy proxy;

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
