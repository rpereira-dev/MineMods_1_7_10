package com.rpereira.minemod.common.spells.mage;

import com.rpereira.minemod.common.spells.SpellMineMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class SpellTransfert extends SpellMineMod {

	@Override
	public void playAnimation(Entity caster, Entity target) {
		this.processSpell(caster, target);
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		double x1 = caster.posX;
		double y1 = caster.posY;
		double z1 = caster.posZ;

		double x2 = target.posX;
		double y2 = target.posY;
		double z2 = target.posZ;

		float pitch = caster.rotationPitch;
		float yaw = caster.rotationYaw;

		target.setPositionAndRotation(x1, y1, z1, yaw, pitch);
		caster.setPositionAndRotation(x2, y2, z2, yaw + 180, -pitch);
	}

	@Override
	public Entity getTarget(Entity caster) {
		Entity target = super.getLookingEntity((EntityLivingBase) caster, this.getRange());
		return (target);
	}

	@Override
	public String getUnlocalizedName() {
		return ("transfert");
	}

	public int getCost() {
		return (200);
	}

	public int getRequiredLevel() {
		return (1);
	}

	@Override
	public float getRange() {
		return (32.0f);
	}

	@Override
	public boolean requireNonNullTarget() {
		return (true);
	}

}
