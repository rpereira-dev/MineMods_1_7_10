package com.rpereira.minespells.common;

import com.rpereira.minespells.MineSpells;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;

/** an iterface which represent a spell */
public abstract class Spell {

	/** the spell id */
	private final int id;

	public Spell() {
		this.id = MineSpells.getNextSpellID();
	}

	/** animation of the spell */
	@SideOnly(Side.CLIENT)
	public abstract void playAnimation(Entity caster, Entity target);

	/** process the spell effect */
	public abstract void processSpell(Entity caster, Entity target);

	/**
	 * @see MineSpells.getNextSpellID()
	 * @return the spell ID
	 */
	public final int getID() {
		return (this.id);
	}
}
