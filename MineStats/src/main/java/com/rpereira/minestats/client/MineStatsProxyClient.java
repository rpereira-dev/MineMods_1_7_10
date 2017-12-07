package com.rpereira.minestats.client;

import com.rpereira.minestats.MineStats;
import com.rpereira.minestats.common.MineStatsProxy;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class MineStatsProxyClient extends MineStatsProxy {

	@Override
	public void preInit() {
		super.preInit();
		Logger.get().log(Logger.Level.FINE, "PreInit MineStatsProxyClient");
	}

	@Override
	public void init() {
		super.init();
		Logger.get().log(Logger.Level.FINE, "Init MineStatsProxyClient");
	}

	@SubscribeEvent
	public void renderTooltip(ItemTooltipEvent event) {
		Stats stats = MineStats.getItemStackStats(event.itemStack);
		if (stats != null) {
			event.toolTip.addAll(stats.toStringList());
		}
	}
}
