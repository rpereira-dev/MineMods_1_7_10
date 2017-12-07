package com.rpereira.mineclass.common;

import java.util.Collection;
import java.util.HashMap;

import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.mineclass.common.exp.MineClassExp;
import com.rpereira.mineclass.common.packet.Packets;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class MineClassProxy {

	/** the registered entity classes */
	private final HashMap<Integer, EntityClass> entityClasses;

	/** the loaded class instances */
	private final HashMap<EntityLivingBase, EntityClassInstance> entityClassesInstances;

	public MineClassProxy() {
		this.entityClasses = new HashMap<Integer, EntityClass>();
		this.entityClassesInstances = new HashMap<EntityLivingBase, EntityClassInstance>();
	}

	public void preInit() {
		Logger.get().log(Logger.Level.FINE, "PreInit MineClassProxy");
		MineClassExp.preInit();
		MineClassStats.preInit();
		Packets.preInit();
	}

	public void init() {
		Logger.get().log(Logger.Level.FINE, "Init MineClassProxy");
		MineClassExp.init();
		MineClassStats.init();

		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {
		EntityLivingBase died = event.entityLiving;
		if (died != null) {
			EntityClassInstance diedInstance = this.getEntityClassInstance(died);
			if (diedInstance != null) {
				diedInstance.onEntityDied(event.source);
				this.removeEntityClassInstance(diedInstance);
			}
		}
	}

	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event) {

		float amount = event.ammount;
		Entity attacker = event.source.getEntity();
		EntityLivingBase attacked = event.entityLiving;
		DamageSource damageSource = event.source;

		if (attacker != null && attacker instanceof EntityLivingBase) {
			EntityClassInstance attackerInstance = this.getEntityClassInstance((EntityLivingBase) attacker);
			if (attackerInstance != null) {
				attackerInstance.onAttack(attacked, damageSource, amount);
			}
		}

		if (attacked != null) {
			EntityClassInstance attackedInstance = this.getEntityClassInstance(attacked);
			if (attackedInstance != null) {
				attackedInstance.onBeingAttacked(attacker, damageSource, amount);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
		EntityClassInstance entityClassInstance = this.getEntityClassInstance(event.player);
		if (entityClassInstance != null) {
			entityClassInstance.onRespawn();
		} else {
			entityClassInstance = new EntityClassInstance(event.player);
			this.spawnEntityClassInstance(entityClassInstance);
		}
	}

	/** called when a player logs in */
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		EntityClassInstance entityClassInstance = new EntityClassInstance(event.player);
		Logger.get().log(Logger.Level.FINE, "Player logged in, setting class", event.player.getCommandSenderName(),
				entityClassInstance.getEntityClass().getUnlocalizedName());
		this.spawnEntityClassInstance(entityClassInstance);
	}

	/** called when a player logs out */
	@SubscribeEvent
	public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
		this.removeEntityClassInstance(event.player);
	}

	/** called on a server tick */
	@SubscribeEvent
	public void onServerUpdate(TickEvent.ServerTickEvent event) {
		// update every class instances
		for (EntityClassInstance entityClassInstance : this.entityClassesInstances.values()) {
			entityClassInstance.update();
		}
	}

	/** get the default class */
	private EntityClass getDefaultEntityClass() {
		return (this.entityClasses.get(0));
	}

	/** remove the gievn instance */
	public final void removeEntityClassInstance(EntityClassInstance entityClassInstance) {
		this.entityClassesInstances.remove(entityClassInstance);
	}

	/** remove the given instance */
	public final EntityClassInstance removeEntityClassInstance(EntityLivingBase entityLivingBase) {
		return (this.entityClassesInstances.remove(entityLivingBase));
	}

	/** get all the entity class instances */
	public HashMap<EntityLivingBase, EntityClassInstance> getEntityClassInstances() {
		return (this.entityClassesInstances);
	}

	/** add the class instance to the updater */
	public final EntityClassInstance spawnEntityClassInstance(EntityClassInstance entityClassInstance) {
		if (entityClassInstance.getEntity() != null) {
			this.entityClassesInstances.put(entityClassInstance.getEntity(), entityClassInstance);
			Logger.get().log(Logger.Level.FINE, "Attach an EntityClassInstance to an Entity: " + entityClassInstance);
			return (entityClassInstance);
		}
		Logger.get().log(Logger.Level.WARNING, "Tried to attach an entity class to a NULL entity",
				entityClassInstance.getEntityClass().getUnlocalizedName());
		return (null);
	}

	/** register a new entity class */
	public final void registerClass(EntityClass entityClass) {
		this.entityClasses.put(entityClass.getID(), entityClass);
	}

	/** return the EntityClass fort he given ID */
	public final EntityClass getEntityClassForID(int id) {
		return (this.entityClasses.get(id));
	}

	/** get the class instance for the gien entity */
	public EntityClassInstance getEntityClassInstance(EntityLivingBase entityLivingBase) {
		return (this.entityClassesInstances.get(entityLivingBase));
	}

	public final Collection<EntityClass> getEntityClasses() {
		return (this.entityClasses.values());
	}

}