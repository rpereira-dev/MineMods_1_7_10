package com.rpereira.minemod;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineexp.MineExp;
import com.rpereira.minemod.common.IMineModProxy;
import com.rpereira.minemod.common.MineModProxyCommon;
import com.rpereira.minespells.MineSpells;
import com.rpereira.minestats.MineStats;
import com.rpereira.mineteam.MineTeam;
import com.rpereira.mineutils.Logger;
import com.rpereira.mineutils.MineUtils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = MineMod.MODID, name = MineMod.NAME, version = MineMod.VERSION, dependencies = "required-after:"
		+ MineStats.MODID + ";required-after:" + MineSpells.MODID + ";required-after:" + MineExp.MODID
		+ ";required-after:" + MineUtils.MODID + ";required-after:" + MineClass.MODID + ";required-after:"
		+ MineTeam.MODID)
public class MineMod {
	public static final String MODID = "minemod";
	public static final String NAME = "MineMod";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = "com.rpereira.minemod.client.MineModProxyClient", serverSide = "com.rpereira.minemod.common.MineModProxyCommon")
	public static MineModProxyCommon proxy;

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
	public static final IMineModProxy proxy() {
		return (proxy);
	}
}
