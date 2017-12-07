package com.rpereira.minemod.common.spells;

import java.util.Random;

import com.rpereira.minemod.common.entity.EntitySummonable;
import com.rpereira.minespells.client.EntityFXSpell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public abstract class SpellSummon extends SpellMineMod {

	@Override
	public void playAnimation(Entity caster, Entity target) {
		World world = Minecraft.getMinecraft().theWorld;
		Random rng = world.rand;
		double x = caster.posX;
		double y = caster.posY;
		double z = caster.posZ;
		for (int i = 0; i < 20; ++i) {
			this.spawnParticleOnce(world, world.rand, x, y, z);
			this.spawnParticleOnce(world, world.rand, x, y, z);
			this.spawnParticleOnce(world, world.rand, x, y, z);
			this.spawnParticleOnce(world, world.rand, x, y, z);
			this.spawnParticleOnce(world, world.rand, x, y, z);
			this.spawnParticleOnce(world, world.rand, x, y, z);
			this.spawnParticleOnce(world, world.rand, x, y, z);
			this.spawnParticleOnce(world, world.rand, x, y, z);
			this.spawnParticleOnce(world, world.rand, x, y, z);
		}
	}

	private void spawnParticleOnce(World world, Random rng, double x, double y, double z) {
		float vx = rng.nextFloat();
		float vy = rng.nextFloat();
		float vz = rng.nextFloat();

		if (rng.nextInt(2) == 0) {
			vx = -vx;
		}

		if (rng.nextInt(2) == 0) {
			vy = -vy;
		}

		if (rng.nextInt(2) == 0) {
			vz = -vz;
		}

		float scale = this.getScale() * (0.75f + rng.nextFloat() * 0.5f);
		float r = this.getR() * (0.75f + rng.nextFloat() * 0.5f);
		float g = this.getG() * (0.75f + rng.nextFloat() * 0.5f);
		float b = this.getB() * (0.75f + rng.nextFloat() * 0.5f);
		EntityFX particle = new EntityFXSpell(world, x, y, z, vx, vy, vz, scale, r, g, b);
		super.spawnParticle(particle);
	}

	@Override
	public void processSpell(Entity caster, Entity target) {
		EntitySummonable[] toSummon = this.entityToSummon(caster);
		if (toSummon == null) {
			return;
		}

		float magic = this.getEntityMagic(caster);
		float attack = this.getEntityDefaultDamages() + this.getEntityDamageRatio() * magic;
		float health = this.getEntityDefaultHealth() + this.getEntityHealthRatio() * magic;
		for (EntitySummonable entity : toSummon) {
			caster.worldObj.spawnEntityInWorld(entity);
			entity.onSummoned(caster);
			entity.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attack);
			entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(health);
		}
	}

	/** particle effect color */
	public abstract float getR();

	/** particle effect color */
	public abstract float getG();

	/** particle effect color */
	public abstract float getB();

	/** particle effect scale */
	public abstract float getScale();

	/**
	 * get the entity to summon
	 * 
	 * @param caster
	 */
	public abstract EntitySummonable[] entityToSummon(Entity caster);

	public abstract float getEntityDefaultHealth();

	public abstract float getEntityHealthRatio();

	public abstract float getEntityDefaultDamages();

	public abstract float getEntityDamageRatio();
}
