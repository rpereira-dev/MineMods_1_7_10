package com.rpereira.minemod.common.spells.mage;

import com.rpereira.minemod.common.spells.SpellMineMod;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SpellFireCone extends SpellMineMod {

	private void animationCone(World world, double x, double y, double z, float pi, int height) {
		float radius = 1 + height / 5.0f;
		float dx = radius * MathHelper.cos(pi);
		float dy = 0.0F;
		float dz = radius * MathHelper.sin(pi);
		float vx = dx / 16.0F;
		float vy = (dy + height * 0.5f) / 8.0f;
		float vz = dz / 16.0f;

		world.spawnParticle("flame", x, y, z, vx, vy, vz);
	}

	@Override
	public void playAnimation(Entity caster, Entity target) {
		double x = target.posX;
		double y = target.posY;
		double z = target.posZ;
		World world = Minecraft.getMinecraft().theWorld;
		int height;
		float radius, dx, dy, dz, vx, vy, vz;
		for (int pi = 0; pi < 360; pi += 4) {
			this.animationCone(world, x, y, z, pi, 0);
			this.animationCone(world, x, y, z, pi, 1);
			this.animationCone(world, x, y, z, pi, 2);
			this.animationCone(world, x, y, z, pi, 3);
			this.animationCone(world, x, y, z, pi, 4);
			this.animationCone(world, x, y, z, pi, 5);
			this.animationCone(world, x, y, z, pi, 6);
			this.animationCone(world, x, y, z, pi, 7);
		}
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		if (target instanceof EntityLivingBase) {
			super.dealDamages(caster, target);
			if (caster.worldObj.rand.nextInt(3) == 0) {
				target.setFire(3);
			}
		}
	}

	@Override
	public float getBaseDamages() {
		return (4.0F);
	}

	@Override
	public Entity getTarget(Entity caster) {
		Entity target = super.getLookingEntity((EntityLivingBase) caster, this.getRange());
		return (target);
	}

	@Override
	public String getUnlocalizedName() {
		return ("firecon");
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
