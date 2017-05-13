package com.rpereira.mineutils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = MineUtils.MODID, name = MineUtils.NAME, version = MineUtils.VERSION)
public class MineUtils {
	public static final String MODID = "mineutils";
	public static final String NAME = "MineUtils";
	public static final String VERSION = "1.0";

	@EventHandler
	public void preInit(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "PreInit");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "Init");
	}
}
