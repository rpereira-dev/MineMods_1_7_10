package com.rpereira.minemod.common.spells;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public abstract class SpellBuff extends SpellMineMod {

	@Override
	public void playAnimation(Entity caster, Entity target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		if (target instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase) target;
			EntityClassInstance entityClassInstance = MineClass.proxy().getEntityClassInstance(entity);
			if (entityClassInstance != null) {
				this.processSpell(entityClassInstance);
			}
		}
	}

	private void processSpell(EntityClassInstance entityClassInstance) {
		// TODO Auto-generated method stub
		
	}
}
