package com.rpereira.mineclass;

import com.rpereira.mineclass.client.MineClassProxyClient;
import com.rpereira.mineclass.common.MineClassProxy;
import com.rpereira.minespells.MineSpells;
import com.rpereira.minestats.MineStats;
import com.rpereira.mineutils.Logger;
import com.rpereira.mineutils.MineUtils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = MineClass.MODID, name = MineClass.NAME, version = MineClass.VERSION, dependencies = "required-after:"
		+ MineStats.MODID + ";required-after:" + MineSpells.MODID + ";required-after:" + MineUtils.MODID)
public class MineClass {
	public static final String MODID = "mineclass";
	public static final String NAME = "MineClass";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = "com.rpereira.mineclass.client.MineClassProxyClient", serverSide = "com.rpereira.mineclass.common.MineClassProxy")
	public static MineClassProxy proxy;

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

	/** the proxy instance */
	public static final MineClassProxy proxy() {
		return (proxy);
	}

	@SideOnly(Side.CLIENT)
	public static final MineClassProxyClient proxyClient() {
		return ((MineClassProxyClient) proxy);
	}
}
