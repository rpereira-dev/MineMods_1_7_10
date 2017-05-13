package com.rpereira.mineclass.common.classes;

import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassFarmer extends EntityClass {

	public EntityClassFarmer() {
		super(MineClassStats.STAT_ENERGY, "farmer", ChatColor.WHITE);
	}

}