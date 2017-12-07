package com.rpereira.minemod.common.classes;

import com.rpereira.mineclass.common.classes.EntityClassMana;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.minemod.common.spells.MineModSpells;
import com.rpereira.minemod.common.stats.MineModStats;
import com.rpereira.mineutils.ChatColor;

public class EntityClassPriest extends EntityClassMana {

	public EntityClassPriest() {
		super("minemod:textures/gui/classes/priest.png", "priest", ChatColor.AQUA);

		// add spells
		super.addSpell(MineModSpells.PURIFICATION);
		super.addSpell(MineModSpells.HEAL);
		super.addSpell(MineModSpells.REGENERATION);
		super.addSpell(MineModSpells.HOLY_BLAZE_SUMMON);

		// add stats
		super.setDefaultStat(MineModStats.STAT_CLARITY, MineModStats.STAT_CLARITY.getDefaultValue());
		super.setDefaultStat(MineModStats.STAT_SPIRIT, MineModStats.STAT_SPIRIT.getDefaultValue());
		super.setDefaultStat(MineClassStats.STAT_MANA_PER_SEC, MineClassStats.STAT_MANA_PER_SEC.getDefaultValue());

		// set stats per level
		super.setStatPerLevel(MineClassStats.STAT_MAX_MANA, 10);
		super.setStatPerLevel(MineModStats.STAT_CLARITY, 1);
		super.setStatPerLevel(MineModStats.STAT_SPIRIT, 1);
		super.setStatPerLevel(MineModStats.STAT_STAMINA, 1);
	}

	@Override
	public int getRGBColor() {
		return (0xFFFFFFFF);
	}

	@Override
	public String[] getAdvices() {
		return (new String[] { ChatColor.GREEN + "+ " + MineModStats.STAT_CLARITY.getName() + ChatColor.RESET,
				ChatColor.GREEN + "+ " + MineClassStats.STAT_MANA.getName() + ChatColor.RESET,
				ChatColor.GREEN + "+ " + MineClassStats.STAT_MANA_PER_SEC.getName() + ChatColor.RESET,
				ChatColor.RED + "- " + MineModStats.STAT_STRENGTH.getName() + ChatColor.RESET,
				ChatColor.RED + "- " + MineModStats.STAT_AGILITY.getName() + ChatColor.RESET, });
	}
}