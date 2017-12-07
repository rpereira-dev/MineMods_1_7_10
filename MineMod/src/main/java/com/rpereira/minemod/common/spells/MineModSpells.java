package com.rpereira.minemod.common.spells;

import com.rpereira.minemod.common.IMineModProxy;
import com.rpereira.minemod.common.spells.champion.SpellCharge;
import com.rpereira.minemod.common.spells.champion.SpellCleave;
import com.rpereira.minemod.common.spells.champion.SpellShockwave;
import com.rpereira.minemod.common.spells.mage.SpellFieldDestruction;
import com.rpereira.minemod.common.spells.mage.SpellFireCone;
import com.rpereira.minemod.common.spells.mage.SpellFreezeCube;
import com.rpereira.minemod.common.spells.mage.SpellTransfert;
import com.rpereira.minemod.common.spells.necromancer.SpellDrain;
import com.rpereira.minemod.common.spells.necromancer.SpellSummonZombie;
import com.rpereira.minemod.common.spells.priest.SpellHeal;
import com.rpereira.minemod.common.spells.priest.SpellPurification;
import com.rpereira.minemod.common.spells.priest.SpellRegeneration;
import com.rpereira.minemod.common.spells.priest.SpellSummonHolyBlaze;
import com.rpereira.minespells.MineSpells;
import com.rpereira.minespells.common.Spell;

public class MineModSpells implements IMineModProxy {

	// champion
	public static final Spell CHARGE = new SpellCharge();
	public static final Spell SHOCKWAVE = new SpellShockwave();
	public static final Spell CLEAVE = new SpellCleave();

	// mage
	public static final Spell FIRECONE = new SpellFireCone();
	public static final Spell FREEZECUBE = new SpellFreezeCube();
	public static final Spell TRANSFERT = new SpellTransfert();
	public static final Spell FIElD_DESTRUCTION = new SpellFieldDestruction();

	// necromancer
	public static final Spell ZOMBIE_SUMMON = new SpellSummonZombie();
	public static final Spell DRAIN = new SpellDrain();

	// priest
	public static final Spell HOLY_BLAZE_SUMMON = new SpellSummonHolyBlaze();
	public static final Spell HEAL = new SpellHeal();
	public static final Spell REGENERATION = new SpellRegeneration();
	public static final Spell PURIFICATION = new SpellPurification();

	@Override
	public void preInit() {
	}

	@Override
	public void init() {
		// champion
		MineSpells.registerSpell(CHARGE);
		MineSpells.registerSpell(SHOCKWAVE);
		MineSpells.registerSpell(CLEAVE);

		// mage
		MineSpells.registerSpell(FIRECONE);
		MineSpells.registerSpell(FREEZECUBE);
		MineSpells.registerSpell(TRANSFERT);
		MineSpells.registerSpell(FIElD_DESTRUCTION);

		// necromancer
		MineSpells.registerSpell(DRAIN);
		MineSpells.registerSpell(ZOMBIE_SUMMON);

		// priest
		MineSpells.registerSpell(HOLY_BLAZE_SUMMON);
		MineSpells.registerSpell(HEAL);
		MineSpells.registerSpell(REGENERATION);
		MineSpells.registerSpell(PURIFICATION);

	}
}
