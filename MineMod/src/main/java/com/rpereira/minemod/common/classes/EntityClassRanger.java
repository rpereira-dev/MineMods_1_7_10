package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassRanger extends EntityClass {

	public EntityClassRanger() {
		super(MineClassStats.STAT_ENERGY, "ranger", ChatColor.GOLD);
	}

}