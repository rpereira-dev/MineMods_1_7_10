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

		@Override
		public float getDefaultValue() {
			return (1000);
		}
	};

	/** the max rage stat */
	public static final Stat STAT_MAX_RAGE = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("maxrage");
		}

		@Override
		public float getDefaultValue() {
			return (100);
		}
	};

	/** the max energy stat */
	public static final Stat STAT_MAX_ENERGY = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("maxenergy");
		}

		@Override
		public float getDefaultValue() {
			return (100);
		}
	};

	/** the mana stat */
	public static final StatResource STAT_MANA = new StatResource(STAT_MAX_MANA) {
		@Override
		public String getUnlocalizedName() {
			return ("mana");
		}

		@Override
		public float getDefaultValue() {
			return (STAT_MAX_MANA.getDefaultValue());
		}
	};

	/** the rage stat */
	public static final StatResource STAT_RAGE = new StatResource(STAT_MAX_RAGE) {
		@Override
		public String getUnlocalizedName() {
			return ("rage");
		}

		@Override
		public float getDefaultValue() {
			return (0);
		}
	};

	/** the energy stat */
	public static final StatResource STAT_ENERGY = new StatResource(STAT_MAX_ENERGY) {
		@Override
		public String getUnlocalizedName() {
			return ("energy");
		}

		@Override
		public float getDefaultValue() {
			return (STAT_MAX_ENERGY.getDefaultValue());
		}
	};

	/** the magic stat */
	public static final Stat STAT_MANA_PER_SEC = new Stat() {
		@Override
		public float getDefaultValue() {
			return (1.0f);
		}

		@Override
		public String getUnlocalizedName() {
			return ("mana_per_sec");
		}
	};

	public static void preInit() {
	}

	public static void init() {
		MineStats.registerStat(STAT_MANA);
		MineStats.registerStat(STAT_MAX_MANA);
		MineStats.registerStat(STAT_MANA_PER_SEC);
		MineStats.registerStat(STAT_RAGE);
		MineStats.registerStat(STAT_MAX_RAGE);
		MineStats.registerStat(STAT_ENERGY);
		MineStats.registerStat(STAT_MAX_ENERGY);

	}
}
