package com.rpereira.minespells.common;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public class SpellUtils {
	// public static void deal_player_magic_damages(EntityPlayer caster,
	// EntityLivingBase target, float amount) {
	// target.attackEntityFrom(DamageSource.causePlayerDamage(caster), amount);
	// target.attackEntityFrom(DamageSource.magic, 0.0f);
	// }

	/** Get entity this Entity around caster */
	public static List<?> getEntitiesAround(Entity caster, double rangex, double rangey, double rangez) {
		List<?> lst = caster.worldObj.getEntitiesWithinAABBExcludingEntity(caster,
				caster.getBoundingBox().expand(rangex, rangey, rangez));
		return (lst);
	}

	/** Get entity this EntityLivingBase is looking at */
	public static Entity getLookingEntity(EntityLivingBase caster, double range) {

		List<?> list = caster.worldObj.getEntitiesWithinAABBExcludingEntity(caster,
				caster.getBoundingBox().expand(range, range, range));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (!entity.isDead) {
				if (entity instanceof EntityLivingBase) {
					Vec3 vec3d = caster.getLook(1.0F).normalize();
					Vec3 vec3d1 = Vec3
							.createVectorHelper(entity.posX - caster.posX,
									(entity.getBoundingBox().minY + (double) (entity.height / 2.0F))
											- (caster.posY + (double) caster.getEyeHeight()),
									entity.posZ - caster.posZ);
					double d = vec3d1.lengthVector();
					vec3d1 = vec3d1.normalize();
					double d1 = vec3d.dotProduct(vec3d1);
					if (d1 > 1.0D - 0.0250001D / d) {
						return (entity);
					}
				}
			}
		}
		return (null);
	}
}
