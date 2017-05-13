package com.rpereira.mineclass.common.stats;

import com.rpereira.minestats.MineStats;
import com.rpereira.minestats.common.Stat;

public class MineClassStats {

	/** the max mana stat */
	public static final Stat STAT_MAX_MANA = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("maxmana");
		}
	};

	/** the max rage stat */
	public static final Stat STAT_MAX_RAGE = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("maxrage");
		}
	};

	/** the max energy stat */
	public static final Stat STAT_MAX_ENERGY = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("maxenergy");
		}
	};

	/** the mana stat */
	public static final StatResource STAT_MANA = new StatResource(STAT_MAX_MANA) {
		@Override
		public String getUnlocalizedName() {
			return ("mana");
		}
	};

	/** the rage stat */
	public static final StatResource STAT_RAGE = new StatResource(STAT_MAX_RAGE) {
		@Override
		public String getUnlocalizedName() {
			return ("rage");
		}
	};

	/** the energy stat */
	public static final StatResource STAT_ENERGY = new StatResource(STAT_MAX_ENERGY) {
		@Override
		public String getUnlocalizedName() {
			return ("energy");
		}
	};

	public static void preInit() {
	}

	public static void init() {
		MineStats.registerStat(STAT_MANA);
		MineStats.registerStat(STAT_MAX_MANA);
		MineStats.registerStat(STAT_RAGE);
		MineStats.registerStat(STAT_MAX_RAGE);
		MineStats.registerStat(STAT_ENERGY);
		MineStats.registerStat(STAT_MAX_ENERGY);
	}
}
