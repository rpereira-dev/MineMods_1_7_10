package com.rpereira.minemod.common.spells.champion;

import java.util.List;
import java.util.Random;

import com.rpereira.minemod.common.spells.SpellMineMod;
import com.rpereira.minespells.client.EntityFXSpell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellCleave extends SpellMineMod {

	private static final double RANGE_X = 5.0d;
	private static final double RANGE_Y = 5.0d;
	private static final double RANGE_Z = 5.0d;

	@Override
	public void playAnimation(Entity caster, Entity target) {

		World world = caster.worldObj;
		Random rng = world.rand;
		double x = caster.posX;
		double y = caster.posY - 0.2f;
		double z = caster.posZ;
		double vx, vz;
		for (int i = 0; i < 25; i++) {
			EntityFX particle = new EntityFXSpell(world, x, y, z, 0, 0, 0, 2.0f, 0, 2.5f, 0.8f);
			super.spawnParticle(particle);
		}
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		if (caster == null) {
			return;
		}
		List<?> list = super.getEntitiesAround(caster, RANGE_X, RANGE_Y, RANGE_Z);
		for (Object obj : list) {
			if (obj instanceof Entity) {

				target = (Entity) obj;

				double vx = caster.posX - target.posX;
				double vy = caster.posY - target.posY;
				double vz = caster.posZ - target.posZ;
				double eyex = caster.getLookVec().xCoord;
				double eyey = caster.getLookVec().yCoord;
				double eyez = caster.getLookVec().zCoord;

				if (vx * eyex + vy * eyey + vz * eyez < 0.0f) {
					super.dealDamages(caster, target);
				}
			}
		}
	}

	@Override
	public float getBaseDamages() {
		return (2.0f);
	}

	@Override
	public float getPowerRatio() {
		return (1 / 128.0f);
	}

	@Override
	public int getCost() {
		return (30);
	}

	@Override
	public int getRequiredLevel() {
		return (1);
	}

	@Override
	public String getUnlocalizedName() {
		return ("cleave");
	}
	
	@Override
	public Entity getTarget(Entity caster) {
		Entity target = super.getLookingEntity((EntityLivingBase) caster, this.getRange());
		return (target);
	}
	
	@Override
	public float getRange() {
		return (8.0f);
	}
}
