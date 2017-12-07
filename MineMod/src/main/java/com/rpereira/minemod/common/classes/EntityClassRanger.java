package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.common.classes.EntityClassEnergy;
import com.rpereira.minemod.common.stats.MineModStats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassRanger extends EntityClassEnergy {

	public EntityClassRanger() {
		super("minemod:textures/gui/classes/ranger.png", "ranger", ChatColor.GOLD);

		// add stats
		super.setDefaultStat(MineModStats.STAT_STRENGTH, MineModStats.STAT_STRENGTH.getDefaultValue());
		super.setDefaultStat(MineModStats.STAT_AGILITY, MineModStats.STAT_AGILITY.getDefaultValue());

		// stats per level
		super.setStatPerLevel(MineModStats.STAT_STRENGTH, 0.5f);
		super.setStatPerLevel(MineModStats.STAT_AGILITY, 1.5f);
		super.setStatPerLevel(MineModStats.STAT_STAMINA, 1);
	}

	@Override
	public int getRGBColor() {
		return (0xFFAAAA00);
	}

	@Override
	public String[] getAdvices() {
		return (new String[] { ChatColor.GREEN + "+ " + MineModStats.STAT_STAMINA.getName() + ChatColor.RESET,
				ChatColor.GREEN + "+ " + MineModStats.STAT_STRENGTH.getName() + ChatColor.RESET,
				ChatColor.GREEN + "+ " + MineModStats.STAT_POWER.getName() + ChatColor.RESET,
				ChatColor.RED + "- " + MineModStats.STAT_CLARITY.getName() + ChatColor.RESET, });
	}
}