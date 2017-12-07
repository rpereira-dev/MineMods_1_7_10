package com.rpereira.minespells.common;

import java.util.List;

import com.rpereira.minespells.MineSpells;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;

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

	/**
	 * process the spell effect
	 * 
	 * @param world
	 */
	public abstract void processSpell(Entity caster, Entity target);

	/** unlocalized name */
	public abstract String getUnlocalizedName();

	public final String getName() {
		return (I18n.format("spell." + this.getUnlocalizedName()));
	}

	/**
	 * @see MineSpells.getNextSpellID()
	 * @return the spell ID
	 */
	public final int getID() {
		return (this.id);
	}

	public int getCost() {
		return (0);
	}

	public int getRequiredLevel() {
		return (0);
	}

	public float getRange() {
		return (32.0F);
	}

	public float getBaseDamages() {
		return (0.0F);
	}

	public float getMagicRatio() {
		return (0.0f);
	}

	public float getPowerRatio() {
		return (0.0f);
	}

	public boolean requireNonNullTarget() {
		return (false);
	}

	public Entity getTarget(Entity caster) {
		return null;
	}

	/** Get entity this Entity around caster */
	public static List<?> getEntitiesAround(Entity caster, double rangex, double rangey, double rangez) {
		List lst = caster.worldObj.getEntitiesWithinAABBExcludingEntity(caster,
				caster.boundingBox.expand(rangex, rangey, rangez));
		return (lst);
	}

	/** Get entity this EntityLivingBase is looking at */
	public static Entity getLookingEntity(EntityLivingBase caster, double range) {
		List list = caster.worldObj.getEntitiesWithinAABBExcludingEntity(caster,
				caster.boundingBox.expand(range, range, range));

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Entity entity = (Entity) list.get(i);

				if (!entity.isDead) {
					if (entity instanceof EntityLivingBase) {
						EntityLivingBase target = (EntityLivingBase) entity;

						Vec3 vec3d = caster.getLook(1.0F).normalize();
						Vec3 vec3d1 = Vec3
								.createVectorHelper(target.posX - caster.posX,
										(target.boundingBox.minY + (double) (target.height / 2.0F))
												- (caster.posY + (double) caster.getEyeHeight()),
										target.posZ - caster.posZ);
						double d = vec3d1.lengthVector();
						vec3d1 = vec3d1.normalize();
						double d1 = vec3d.dotProduct(vec3d1);
						if (d1 > 1.0D - 0.025D / d) {
							return (target);
						}
					}

				}
			}
		}
		return (null);
	}

	public void spawnParticle(EntityFX fx) {
		Minecraft.getMinecraft().effectRenderer.addEffect(fx);
	}

	public final void dealDamages(Entity caster, Entity target, float damage) {
		if (caster instanceof EntityPlayer) {
			target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) caster), damage);
		} else if (caster instanceof EntityLivingBase) {
			target.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase) caster), damage);
		} else {
			target.attackEntityFrom(DamageSource.causeMobDamage(null), damage);
		}
	}

	public String[] getDescription(Entity caster) {
		return (new String[0]);
	}

}
