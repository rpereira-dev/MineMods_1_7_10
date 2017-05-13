package com.rpereira.minemod.common;

import java.util.ArrayList;
import java.util.Random;

import com.rpereira.minemod.common.classes.MineModClasses;
import com.rpereira.minemod.common.exp.MineModExp;
import com.rpereira.minemod.common.spells.MineModSpells;
import com.rpereira.minemod.common.stats.MineModStats;
import com.rpereira.minestats.MineStats;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class MineModProxyCommon implements IMineModProxy {

	protected ArrayList<IMineModProxy> proxies;

	public MineModProxyCommon() {
		this.proxies = new ArrayList<IMineModProxy>();
		this.addProxy(new MineModStats());
		this.addProxy(new MineModSpells());
		this.addProxy(new MineModExp());
		this.addProxy(new MineModClasses());
	}

	public void addProxy(IMineModProxy mineModProxy) {
		this.proxies.add(mineModProxy);
	}

	public void preInit() {
		for (IMineModProxy proxy : this.proxies) {
			Logger.get().log(Logger.Level.FINE, "PreInit", proxy.getClass().getSimpleName());
			proxy.preInit();
		}
	}

	public void init() {
		Logger.get().log(Logger.Level.FINE, "Init MineModProxyCommon");

		for (IMineModProxy proxy : this.proxies) {
			Logger.get().log(Logger.Level.FINE, "Init", proxy.getClass().getSimpleName());
			proxy.init();
		}

		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	/** called when an item is picked up by a player */
	@SubscribeEvent
	public void onItemPickedUp(EntityItemPickupEvent event) {
		ItemStack is = event.item.getEntityItem();
		if (!MineStats.itemStackHasStats(is)) {
			Stats stats = new Stats();
			stats.addStat(MineModStats.STAT_AGILITY, new Random().nextFloat());
			MineStats.setItemStackStats(is, stats);
		}
	}
}