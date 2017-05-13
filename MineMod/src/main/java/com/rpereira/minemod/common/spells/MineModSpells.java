package com.rpereira.minemod.common.spells;

import com.rpereira.minemod.common.IMineModProxy;
import com.rpereira.minemod.common.spells.champion.SpellCharge;
import com.rpereira.minespells.MineSpells;
import com.rpereira.minespells.common.Spell;

public class MineModSpells implements IMineModProxy {
	public static final Spell SPELL_CHARGE = new SpellCharge();

	@Override
	public void preInit() {
	}

	@Override
	public void init() {
		MineSpells.registerSpell(SPELL_CHARGE);
	}
}
