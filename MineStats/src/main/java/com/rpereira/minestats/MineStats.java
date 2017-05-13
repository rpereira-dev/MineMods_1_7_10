package com.rpereira.minestats;

import java.util.HashMap;
import java.util.Map.Entry;

import com.rpereira.minestats.common.MineStatsProxy;
import com.rpereira.minestats.common.Stat;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

@Mod(modid = "minestats", name = "MineStats", version = "1.0")
public class MineStats {
	public static final String MODID = "minestats";
	public static final String NAME = "MineStats";
	public static final String VERSION = "1.0";
	@SidedProxy(clientSide = "com.rpereira.minestats.client.MineStatsProxyClient", serverSide = "com.rpereira.minestats.common.MineStatsProxy")
	public static MineStatsProxy proxy;

	/** the registered stats */
	public static final HashMap<String, Stat> STATS = new HashMap<String, Stat>();

	@Mod.EventHandler
	public void preInit(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "PreInit");
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "Init");
		proxy.init();
	}

	/** register the given stat, should be call during forge init */
	public static boolean registerStat(Stat stat) {
		if (STATS.containsKey(stat.getUnlocalizedName())) {
			return (false);
		}
		STATS.put(stat.getUnlocalizedName(), stat);
		return (true);
	}

	/** return the stats for the given entity */
	public static Stats getEntityStats(EntityLivingBase entity) {
		return getEquipementStats(entity.getLastActiveItems());
	}

	/** retur the combined stats of the given itemstacks */
	public static Stats getEquipementStats(ItemStack... itemStacks) {
		Stats total = new Stats();
		for (ItemStack itemStack : itemStacks) {

			ItemStack is = itemStack;
			NBTTagCompound tag = is.getTagCompound();
			if (tag == null) {
				continue;
			}

			for (Stat stat : STATS.values()) {
				if (tag.hasKey(stat.getUnlocalizedName())) {
					total.addStat(stat, tag.getFloat(stat.getUnlocalizedName()));
				}
			}

		}
		return total;
	}

	/** retur the stats of the given itemstack */
	public static Stats getItemStackStats(ItemStack itemStack) {
		ItemStack is = itemStack;
		NBTTagCompound tag = is.getTagCompound();
		if (tag == null) {
			return (null);
		}
		Stats stats = new Stats();
		for (Stat stat : STATS.values()) {
			if (tag.hasKey(stat.getUnlocalizedName())) {
				stats.addStat(stat, tag.getFloat(stat.getUnlocalizedName()));
			}
		}
		return (stats);
	}

	/** set the stat to the given itemstack */
	public static void setItemStackStats(ItemStack itemStack, Stats stats) {
		NBTTagCompound tag = itemStack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
			itemStack.setTagCompound(tag);
		}

		tag.setBoolean("statsSet", true);
		for (Entry<Stat, Float> entry : stats.getValues()) {
			tag.setFloat(entry.getKey().getUnlocalizedName(), entry.getValue());
		}
	}

	/** return true if some stats were already set for this item */
	public static boolean itemStackHasStats(ItemStack itemStack) {
		NBTTagCompound tag = itemStack.getTagCompound();
		return (tag != null && tag.hasKey("statsSet"));
	}

	/** reset the stat of the given itemstack */
	public static void resetItemStackStats(ItemStack itemStack) {
		NBTTagCompound tag = itemStack.getTagCompound();
		if (tag == null) {
			return;
		}

		for (Stat stat : STATS.values()) {
			if (tag.hasKey(stat.getUnlocalizedName())) {
				tag.removeTag(stat.getUnlocalizedName());
			}
		}
	}
}
