package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.common.classes.EntityClassMana;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.minemod.common.spells.MineModSpells;
import com.rpereira.minemod.common.stats.MineModStats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassHolyBlaze extends EntityClassMana {

	public EntityClassHolyBlaze() {
		super(null, "holy_blaze", ChatColor.WHITE);

		// add spells
		super.addSpell(MineModSpells.HEAL);
		super.addSpell(MineModSpells.REGENERATION);

		// add stats
		super.setDefaultStat(MineModStats.STAT_CLARITY, MineModStats.STAT_CLARITY.getDefaultValue());
		super.setDefaultStat(MineModStats.STAT_SPIRIT, MineModStats.STAT_SPIRIT.getDefaultValue());
		super.setDefaultStat(MineClassStats.STAT_MANA_PER_SEC,
				MineClassStats.STAT_MANA_PER_SEC.getDefaultValue() * 16.0f);
	}

	@Override
	public int getRGBColor() {
		return (0xFF2266AA);
	}

	@Override
	public String[] getAdvices() {
		return (new String[0]);
	}
}