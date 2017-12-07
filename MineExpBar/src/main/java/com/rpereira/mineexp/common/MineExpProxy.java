package com.rpereira.mineexp.common;

import java.util.HashMap;

import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public abstract class MineExpProxy {

	protected final HashMap<Integer, ExpBar> expBars;
	protected final HashMap<Object, ExpBarInstance> expBarInstances;

	public MineExpProxy() {
		this.expBars = new HashMap<Integer, ExpBar>();
		this.expBarInstances = new HashMap<Object, ExpBarInstance>();
	}

	public void preInit() {
		Logger.get().log(Logger.Level.FINE, "PreInit MineExpProxy");
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void init() {
		Logger.get().log(Logger.Level.FINE, "Init MineExpProxy");
	}

	public void registerExpBar(ExpBar expBar) {
		this.expBars.put(expBar.getID(), expBar);
	}

	public ExpBar getExpBar(int id) {
		return (this.expBars.get(id));
	}

	/** add a new expbar instance */
	public ExpBarInstance createExpBarInstance(ExpBar expBar, int uuid) {
		ExpBarInstance expBarInstance = new ExpBarInstance(expBar, uuid);
		this.expBarInstances.put(uuid, expBarInstance);
		return (expBarInstance);
	}

	private int nextExpBarInstanceUUID = 0;

	public ExpBarInstance createExpBarInstance(ExpBar expBar) {
		return (this.createExpBarInstance(expBar, this.nextExpBarInstanceUUID++));
	}

	public void removeExpBarInstance(ExpBarInstance expBarInstance) {
		this.expBarInstances.remove(expBarInstance.getAttribute("uuid"));
	}

	public void removeExpBarInstance(int uuid) {
		this.expBarInstances.remove(uuid);
	}

	public void removeExpBarInstance(Object... attributes) {
		if (attributes.length % 2 != 0) {
			Logger.get().log(Logger.Level.WARNING,
					"removeExpBarInstance() take a pair number of attributes (key, value)");
			return;
		}
		for (ExpBarInstance expBarInstance : this.expBarInstances.values()) {
			int i = 0;
			whileloop: while (i < attributes.length) {
				Object key = attributes[i++];
				Object value = attributes[i++];
				if (expBarInstance.getAttribute(key) != value) {
					continue whileloop;
				}
			}
		}
	}

	public ExpBarInstance getExpBarInstance(Object uuid) {
		return (this.expBarInstances.get(uuid));
	}
}
