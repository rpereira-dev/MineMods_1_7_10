package com.rpereira.mineclass.common.spells;

import com.rpereira.mineclass.common.spells.champion.SpellCharge;
import com.rpereira.minespells.MineSpells;
import com.rpereira.minespells.common.ASpell;

public class MineClassSpells {
	public static final ASpell SPELL_CHARGE = new SpellCharge();

	public static void preInit() {
		MineSpells.registerSpell(SPELL_CHARGE);
	}
}
