package com.rpereira.mineclass.common.classes;

import java.util.HashMap;
import java.util.Map.Entry;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.exp.MineClassExp;
import com.rpereira.mineexp.MineExp;
import com.rpereira.mineexp.common.ExpBarInstance;
import com.rpereira.minespells.common.Spell;
import com.rpereira.minestats.MineStats;
import com.rpereira.minestats.common.Stat;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.Logger;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

/** an instance of a certain EntityClass */
public class EntityClassInstance {

	/** the entity class */
	private EntityClass entityClass;

	/** the entity attach */
	private EntityLivingBase entityLivingBase;

	/** the entity stats */
	private final Stats stats;
	private final Stats equipmentStats;
	private final Stats defaultStats;
	private final Stats statsPerLevel;

	/** the resource stat value */
	private float resource;
	private float maxresource;

	/** the player level */
	private final ExpBarInstance levelExpBarInstance;

	/** attributes */
	private HashMap<Object, Object> attributes;

	/**
	 * @param entityClass
	 *            : the class to attach to the entity
	 * @param entity
	 *            : the entity
	 */
	public EntityClassInstance(EntityLivingBase entityLivingBase) {
		this.stats = new Stats();
		this.defaultStats = new Stats();
		this.equipmentStats = new Stats();
		this.statsPerLevel = new Stats();
		this.levelExpBarInstance = MineExp.createExpBarInstance(MineClassExp.EXP_BAR_LEVEL);
		this.entityLivingBase = entityLivingBase;
		this.resource = 0.0F;
		this.loadFromNBT(entityLivingBase.getEntityData());

		// default stats (per level)
		this.defaultStats.reset();
		for (Stat stat : this.entityClass.getDefaultStats()) {
			this.defaultStats.addStat(stat, this.entityClass.getDefaultStats().get(stat));
		}

		// per level stats
		this.statsPerLevel.reset();
		for (Stat stat : this.entityClass.getStatsPerLevel()) {
			this.statsPerLevel.addStat(stat, this.entityClass.getStatForLevel(stat, this.getLevel()));
		}
	}

	/** set attributes for this instance */
	public final void setAttributes(Object... attributes) {
		if (attributes.length % 2 != 0) {
			Logger.get().log(Logger.Level.WARNING,
					"ExpBarInstance attributes not pair! which mean you forgot a key or a value");
		} else {
			int i = 0;
			while (i < attributes.length) {
				this.setAttribute(attributes[i], attributes[i + 1]);
			}
		}
	}

	/** set attribute */
	public final void setAttribute(Object key, Object value) {
		if (this.attributes == null) {
			this.attributes = new HashMap<Object, Object>();
		}
		this.attributes.put(key, value);
	}

	/** get the attributes */
	public final HashMap<Object, Object> getAttributes() {
		return (this.attributes);
	}

	/** get attribute */
	public final <K, V> V getAttribute(K key) {
		if (this.attributes == null) {
			return (null);
		}
		V attr = (V) this.attributes.get(key);
		return (attr);
	}

	/** get attribute */
	public final <K, V> V getAttribute(K key, V defaultvalue) {
		V attr = this.getAttribute(key);
		return (attr != null ? attr : defaultvalue);
	}

	/** update this instance for the given entity */
	public void update() {

		this.levelExpBarInstance.update();

		// equipment stats
		MineStats.getEquipmentStats(this.equipmentStats, this.entityLivingBase);

		// total stats
		this.stats.reset();
		this.stats.combine(this.defaultStats);
		this.stats.combine(this.equipmentStats);
		this.stats.combine(this.statsPerLevel);

		// max resource
		this.maxresource = this.stats.get(this.entityClass.getResourceStat().getStatmax());

		// gamemode
		if (this.entityLivingBase instanceof EntityPlayer
				&& ((EntityPlayer) this.entityLivingBase).capabilities.isCreativeMode) {
			this.setResource(this.getMaxResource());
		}

		// update entity resources
		this.entityClass.onResourcesUpdated(this);

		// class update
		this.entityClass.updateClassInstance(this);
	}

	/** get the stats */
	public Stats getStats() {
		return (this.stats);
	}

	/** get the entity attach to this instance */
	public EntityLivingBase getEntity() {
		return (this.entityLivingBase);
	}

	/** get the entity class attach to this instance */
	public EntityClass getEntityClass() {
		return (this.entityClass);
	}

	@Override
	public String toString() {
		return ("{" + this.getEntity().toString() + "," + this.getEntityClass().toString() + "}");
	}

	public final void setClass(EntityClass entityClass) {
		if (this.entityClass != entityClass) {
			this.entityClass = entityClass;
			this.saveToNBT(this.entityLivingBase.getEntityData());
		}
	}

	/**
	 * set this instance by loading it from the nbt of the given entity
	 * 
	 * @param entityLivingBase
	 * @return true or false depending on if the entity had a registered class
	 *         in the nbt or no
	 */
	private boolean loadFromNBT(NBTTagCompound tag) {
		int classID = tag.hasKey("classID") ? tag.getInteger("classID") : 0;
		this.setClass(MineClass.proxy().getEntityClassForID(classID));
		return (true);
	}

	/**
	 * save this entityclassinstance to the given nbt
	 * 
	 * @param tag
	 */
	private void saveToNBT(NBTTagCompound tag) {
		tag.setInteger("classID", this.getEntityClass().getID());
	}

	/** called when the entity dies */
	public void onEntityDied(DamageSource source) {
		this.entityClass.onEntityDied(this, source);
	}

	/** called when this entity is attacked */
	public void onBeingAttacked(Entity attacker, DamageSource damageSource, float amount) {
		this.entityClass.onBeingAttacked(this, attacker, damageSource, amount);
	}

	/** called when the entity respawns */
	public void onRespawn() {
		this.entityClass.onRespawn(this);
	}

	/**
	 * called when the entity holding this class instance attack another entity
	 */
	public void onAttack(EntityLivingBase attacked, DamageSource damageSource, float amount) {
		this.entityClass.onAttack(this, attacked, damageSource, amount);
	}

	/** get the mana, energy, or rage */
	public float getResource() {
		return (this.resource);
	}

	public void setResource(float value) {
		this.resource = value;
		if (this.resource < 0) {
			this.resource = 0;
		} else {
			float maxres = this.getMaxResource();
			if (this.resource > maxres) {
				this.resource = maxres;
			}
		}
		this.stats.setStat(this.entityClass.getResourceStat(), this.resource);
	}

	public void addResource(float value) {
		this.setResource(this.resource + value);
	}

	public float getMaxResource() {
		return (this.maxresource);
	}

	public boolean canLaunch(Spell spell) {
		return (spell.getCost() < this.getResource());
	}

	public void launchSpell(Entity target, Spell spell) {
		this.addResource(-spell.getCost());
		this.entityClass.onSpellLaunched(this, target, spell);
	}

	public final int getLevel() {
		return (this.levelExpBarInstance.getLevel());
	}

	public Stats getDefaultStats() {
		return (this.defaultStats);
	}

	public void setStats(HashMap<Stat, Float> stats) {
		this.stats.reset();
		for (Entry<Stat, Float> entry : stats.entrySet()) {
			this.stats.setStat(entry.getKey(), entry.getValue());
		}
	}

	public Spell getRandomSpell() {
		int spellID = this.entityLivingBase.worldObj.rand.nextInt(this.entityClass.getSpells().size());
		return (this.entityClass.getSpell(spellID));
	}
}