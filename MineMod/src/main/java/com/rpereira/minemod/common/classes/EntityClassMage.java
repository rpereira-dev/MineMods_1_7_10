package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassMage extends EntityClass {

	public EntityClassMage() {
		super(MineClassStats.STAT_MANA, "mage", ChatColor.BLUE);
	}

}