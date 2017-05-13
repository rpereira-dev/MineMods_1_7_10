package com.rpereira.mineclass.common.classes;

import java.util.ArrayList;
import java.util.List;

import com.rpereira.mineclass.common.stats.StatResource;
import com.rpereira.minespells.common.Spell;
import com.rpereira.minestats.common.Stat;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineutils.ChatColor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;

/** a class which represent an entity class */
public abstract class EntityClass {

	/** the unlocalized name */
	private final String unlocalizedName;

	/** the chat color */
	private final ChatColor chatColor;

	/** the spells list */
	private final ArrayList<Spell> spells;

	/** the stats per level for this class */
	private final Stats statsPerLevel;

	/** the default stats for this class */
	private final Stats defaultStats;

	/** the resource stat for this class */
	private final StatResource resourceStat;

	/** a unique id */
	private final int id;

	private static int nextID = 0;

	/**
	 * 
	 * @param resourceStat
	 *            : the energy resource stat
	 * @param unlocalizedName
	 *            : the unlocalized name (for description, names...)
	 * @param chatColor
	 *            : the chat color to be used when refering to this class
	 */
	public EntityClass(StatResource resourceStat, String unlocalizedName, ChatColor chatColor) {
		this.resourceStat = resourceStat;
		this.unlocalizedName = unlocalizedName;
		this.chatColor = chatColor;
		this.spells = new ArrayList<Spell>();
		this.statsPerLevel = new Stats();
		this.defaultStats = new Stats();
		this.id = nextID++;
	}

	@SideOnly(Side.CLIENT)
	public void renderClassGUI() {
		// this.renderHealhBar();
		// this.renderExpBar(); ...
		// this.renderEnergyBar(0, 0, 100, 20);
		// TODO Auto-generated method stub
	}

	/**
	 * get the stats per level provided by this class, if NULL or stat not
	 * precised, the factor is set to 0.
	 * 
	 * @see IClass.getStatForLevel()
	 */
	public Stats getStatsPerLevel() {
		return (this.statsPerLevel);
	}

	/**
	 * return the default stats for this class
	 * 
	 * @see IClass.getStatForLevel()
	 **/
	public Stats getDefaultStats() {
		return (this.defaultStats);
	}

	/**
	 * calculate the stat value for the given level of MineExpBar mod:
	 * defaultValue + perLevelValue * level
	 */
	public float getStatForLevel(Stat stat, int level) {
		// the default stats
		Stats defaultStats = this.getDefaultStats();
		float defaultValue = 0.0F;
		if (defaultStats != null) {
			Float value = defaultStats.get(stat);
			if (value != null) {
				defaultValue = value.floatValue();
			}
		}

		// get the stats per level value
		Stats statsPerLevel = this.getStatsPerLevel();
		float perLevelValue = 0.0F;
		if (statsPerLevel != null) {
			Float value = statsPerLevel.get(stat);
			if (value != null) {
				perLevelValue = value.floatValue();
			}
		}

		// return the final value
		return (defaultValue + perLevelValue * level);
	}

	/** get the spell for this class */
	public List<Spell> getSpells() {
		return (this.spells);
	}

	/** add a spell to this class */
	public void addSpell(Spell spell) {
		this.spells.add(spell);
	}

	/** set the default stat per level factor for this class */
	public void setStatPerLevel(Stat stat, float value) {
		this.statsPerLevel.addStat(stat, value);
	}

	/** set the default stat valuef or this class */
	public void setDefaultStat(Stat stat, float value) {
		this.defaultStats.setStat(stat, value);
	}

	/** get the localized string name */
	public String getUnlocalizedName() {
		return (this.unlocalizedName);
	}

	/** get the ChatColor for this class */
	public ChatColor getChatColor() {
		return (this.chatColor);
	}

	/** get the unique id of this class */
	public int getID() {
		return (this.id);
	}

	/** update the given class instance */
	public void updateClassInstance(EntityClassInstance entityClassInstance) {
		// Logger.get().log(Logger.Level.DEBUG, "update: " +
		// entityClassInstance.getEntity());
	}

	/** create a new instance of this EntityClass */
	public EntityClassInstance newInstance(EntityLivingBase entityLivingBase) {
		return (new EntityClassInstance(entityLivingBase, this));
	}

	/** get the resourc estat for this class */
	public StatResource getResourceStat() {
		return (this.resourceStat);
	}

	/** instantiate a new class */
	// public static IClass load_classe_from_ord(int ord) {
	// try {
	// return ((IClasse)
	// (enum_classe.get_classe().getConstructor().newInstance()));
	// } catch (Exception e) {
	// return (new ClasseFarmer());
	// }
	// }
}
