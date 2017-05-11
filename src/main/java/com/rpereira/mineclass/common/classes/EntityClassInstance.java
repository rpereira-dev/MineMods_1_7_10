package com.rpereira.mineclass.common.classes;

import javax.annotation.Nonnull;

import com.rpereira.minestats.common.Stats;

import net.minecraft.entity.EntityLivingBase;

/** an instance of a certain EntityClass */
public class EntityClassInstance {

	/** the entity class */
	private final EntityClass entityClass;

	/** the entity attach */
	private final EntityLivingBase entity;

	/** the entity stats */
	private final Stats stats;

	/**
	 * @param entityClass
	 *            : the class to attach to the entity
	 * @param entity
	 *            : the entity
	 */
	public EntityClassInstance(@Nonnull EntityLivingBase entity, @Nonnull EntityClass entityClass) {
		this.entityClass = entityClass;
		this.stats = new Stats();
		this.entity = entity;
		this.stats.combine(entityClass.getDefaultStats());
		this.stats.addStat(entityClass.getResourceStat(), 0.0F);
	}

	/** update this instance for the given entity */
	public void update() {
		entityClass.updateClassInstance(this);
	}

	/** get the stats */
	public Stats getStats() {
		return (this.stats);
	}

	/** get the entity attach to this instance */
	public EntityLivingBase getEntity() {
		return (this.entity);
	}

	/** get the entity class attach to this instance */
	public EntityClass getEntityClass() {
		return (this.entityClass);
	}

	@Override
	public String toString() {
		return ("{" + this.getEntity().toString() + "," + this.getEntityClass().toString() + "}");
	}
}
