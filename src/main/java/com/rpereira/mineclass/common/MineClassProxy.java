package com.rpereira.mineclass.common;

import java.util.HashMap;
import java.util.Random;

import com.rpereira.mineclass.common.classes.EntityClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.mineclass.common.classes.MineClassClasses;
import com.rpereira.mineclass.common.spells.MineClassSpells;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.minestats.MineStats;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

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
		MineClassStats.preInit();
		MineClassSpells.preInit();
		MineClassClasses.preInit();
	}

	public void init() {
		Logger.get().log(Logger.Level.FINE, "Init MineClassProxy");
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	/** called when an item is picked up by a player */
	@SubscribeEvent
	public void onItemPickedUp(EntityItemPickupEvent event) {
		ItemStack is = event.item.getEntityItem();
		if (!MineStats.itemStackHasStats(is)) {
			Stats stats = new Stats();
			stats.addStat(MineClassStats.STAT_AGILITY, new Random().nextFloat());
			MineStats.setItemStackStats(is, stats);
		}
	}

	/** called on a server tick */
	@SubscribeEvent
	public void onServerUpdate(TickEvent.ServerTickEvent event) {
		// update every class instances
		for (EntityClassInstance entityClassInstance : this.entityClassesInstances.values()) {
			entityClassInstance.update();
		}
	}

	/** called when a player logs in */
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		EntityClassInstance entityClassInstance = this.loadEntityClassInstanceFromNBT(event.player);
		if (entityClassInstance == null) {
			entityClassInstance = this.getDefaultEntityClass().newInstance(event.player);
		}
		Logger.get().log(Logger.Level.FINE, "Player logged in, setting class", event.player.getCommandSenderName(),
				entityClassInstance.getEntityClass().getUnlocalizedName());
		this.spawnEntityClassInstance(entityClassInstance);
	}

	/** called when a player logs out */
	@SubscribeEvent
	public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
		this.removeEntityClassInstance(event.player);
	}

	/** get the default class */
	private EntityClass getDefaultEntityClass() {
		return (this.entityClasses.get(0));
	}

	/** remove the gievn instance */
	public final void removeEntityClassInstance(EntityClassInstance entityClassInstance) {
		this.entityClassesInstances.remove(entityClassInstance);
	}

	/** remove the gievn instance */
	public final void removeEntityClassInstance(EntityLivingBase entityLivingBase) {
		this.entityClassesInstances.remove(entityLivingBase);
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

	/** load an instance from NBT */
	public final EntityClassInstance loadEntityClassInstanceFromNBT(EntityLivingBase entityLivingBase) {
		NBTTagCompound tag = entityLivingBase.getEntityData();
		if (!tag.hasKey("classID")) {
			return (null);
		}
		EntityClass entityClass = this.getEntityClassForID(tag.getInteger("classID"));
		return (entityClass.newInstance(entityLivingBase));
	}

	/** load an instance from NBT */
	public final void saveEntityClassInstanceToNBT(EntityClassInstance entityClassInstance) {
		EntityLivingBase entity = entityClassInstance.getEntity();
		NBTTagCompound tag = entity.getEntityData();
		tag.setInteger("classID", entityClassInstance.getEntityClass().getID());
	}

}