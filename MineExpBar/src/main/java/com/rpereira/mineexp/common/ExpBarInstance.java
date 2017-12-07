package com.rpereira.mineexp.common;

import java.util.HashMap;

import com.rpereira.mineutils.Logger;

import net.minecraft.nbt.NBTTagCompound;

public class ExpBarInstance {

	/** attributes */
	private HashMap<Object, Object> attributes;

	/** current level */
	private int level = 0;

	/** current value */
	private int exp = 0;

	/** the exp bar */
	private final ExpBar expBar;

	/** the uuid */
	private final int uuid;

	public ExpBarInstance(ExpBar expBar, int uuid) {
		this.uuid = uuid;
		this.expBar = expBar;
	}

	public void addLevel() {
		this.addLevels(1);
	}

	public void addLevels(int levels) {
		int newlevel = this.level + levels;
		this.expBar.onExpBarInstanceLevelUp(this, newlevel, this.level);
		this.level = newlevel;
	}

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

	public final void setAttribute(Object key, Object value) {
		if (this.attributes == null) {
			this.attributes = new HashMap<Object, Object>();
		}
		this.attributes.put(key, value);
	}

	public final Object getAttribute(Object key) {
		if (this.attributes == null) {
			return (null);
		}
		return (this.attributes.get(key));
	}

	/** add exp to the experience bar */
	public void addExp(int exp) {
		this.expBar.beforeExpAddToInstance(this);

		int totalexp = this.exp + exp;
		int levelexp = this.expBar.getMaximumExpForLevel(this.level);
		while (totalexp >= levelexp) {
			this.addLevel();
			totalexp -= levelexp;
			levelexp = this.expBar.getMaximumExpForLevel(this.level);
		}
		this.exp = totalexp;

		this.expBar.afterExpAddToInstance(this);
	}

	public void update() {
		this.expBar.onExpBarInstanceUpdate(this);
	}

	public ExpBar getExpBar() {
		return (this.expBar);
	}

	public int getLevel() {
		return (this.level);
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUUID() {
		return (this.uuid);
	}

	public HashMap<Object, Object> getAttributes() {
		return (this.attributes);
	}

	public void writeToNBT(NBTTagCompound nbt) {
		String prefix = String.valueOf(expBar.getID());
		nbt.setBoolean(prefix, true);
		nbt.setInteger(prefix + "level", this.level);
		nbt.setInteger(prefix + "exp", this.exp);
	}

	public void loadFromNBT(NBTTagCompound nbt) {
		String prefix = String.valueOf(this.expBar.getID());

		if (this.attributes != null) {
			this.attributes.clear();
		}

		if (!nbt.hasKey(prefix)) {
			this.exp = 0;
			this.level = 0;
		} else {
			this.exp = nbt.getInteger(prefix + "exp");
			this.level = nbt.getInteger(prefix + "level");
		}
	}
}
