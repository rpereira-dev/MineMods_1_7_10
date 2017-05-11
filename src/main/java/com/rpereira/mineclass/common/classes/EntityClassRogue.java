package com.rpereira.mineclass.common.classes;

import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassRogue extends EntityClass {

	public EntityClassRogue() {
		super(MineClassStats.STAT_ENERGY, "rogue", ChatColor.YELLOW);
	}

}