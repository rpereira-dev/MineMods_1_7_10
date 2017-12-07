package com.rpereira.mineexp;

import com.rpereira.mineexp.common.ExpBar;
import com.rpereira.mineexp.common.ExpBarInstance;
import com.rpereira.mineexp.common.MineExpProxy;
import com.rpereira.mineexp.common.packet.Packets;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = MineExp.MODID, name = MineExp.NAME, version = MineExp.VERSION)
public class MineExp {
	public static final String MODID = "mineexp";
	public static final String NAME = "MineExp";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = "com.rpereira.mineexp.client.MineExpClient", serverSide = "com.rpereira.mineexp.common.MineExpServer")
	public static MineExpProxy proxy;

	@EventHandler
	public void preInit(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "PreInit");
		proxy.preInit();
		Packets.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "Init");
		proxy.init();
	}

	public static void registerExpBar(ExpBar expBar) {
		proxy.registerExpBar(expBar);
	}

	public static ExpBar getExpBar(int expbarid) {
		return (proxy.getExpBar(expbarid));
	}

	public static ExpBarInstance createExpBarInstance(ExpBar expBar, int uuid) {
		return (proxy.createExpBarInstance(expBar, uuid));
	}

	public static ExpBarInstance createExpBarInstance(ExpBar expBar) {
		return (proxy.createExpBarInstance(expBar));
	}
}
