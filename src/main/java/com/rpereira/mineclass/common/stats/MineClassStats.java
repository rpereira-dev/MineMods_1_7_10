package com.rpereira.mineclass.common.stats;

import com.rpereira.minestats.MineStats;
import com.rpereira.minestats.common.Stat;

public class MineClassStats {

	/** the agility stat */
	public static final Stat STAT_AGILITY = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("agility");
		}
	};

	/** the clarity stat */
	public static final Stat STAT_CLARITY = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("clarity");
		}
	};

	/** the power stat */
	public static final Stat STAT_POWER = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("power");
		}
	};

	/** the magic stat */
	public static final Stat STAT_MAGIC = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("magic");
		}
	};

	/** the stamina stat */
	public static final Stat STAT_STAMINA = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("stamina");
		}
	};

	/** the spirit stat */
	public static final Stat STAT_SPIRIT = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("spirit");
		}
	};

	/** the strength stat */
	public static final Stat STAT_STRENGTH = new Stat() {
		@Override
		public String getUnlocalizedName() {
			return ("strength");
		}
	};

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
		MineStats.registerStat(STAT_AGILITY);
		MineStats.registerStat(STAT_CLARITY);
		MineStats.registerStat(STAT_MAGIC);
		MineStats.registerStat(STAT_POWER);
		MineStats.registerStat(STAT_STRENGTH);
		MineStats.registerStat(STAT_STAMINA);
		MineStats.registerStat(STAT_SPIRIT);
		MineStats.registerStat(STAT_MANA);
		MineStats.registerStat(STAT_MAX_MANA);
		MineStats.registerStat(STAT_RAGE);
		MineStats.registerStat(STAT_MAX_RAGE);
		MineStats.registerStat(STAT_ENERGY);
		MineStats.registerStat(STAT_MAX_ENERGY);
	}
}
