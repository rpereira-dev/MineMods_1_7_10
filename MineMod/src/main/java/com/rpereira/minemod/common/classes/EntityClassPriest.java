package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassPriest extends EntityClass {

	public EntityClassPriest() {
		super(MineClassStats.STAT_MANA, "priest", ChatColor.AQUA);
	}

}