package com.rpereira.minemod.common.stats;

import com.rpereira.minemod.common.IMineModProxy;
import com.rpereira.minestats.MineStats;
import com.rpereira.minestats.common.Stat;

public class MineModStats implements IMineModProxy {

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

	@Override
	public void preInit() {
	}

	@Override
	public void init() {
		MineStats.registerStat(STAT_AGILITY);
		MineStats.registerStat(STAT_CLARITY);
		MineStats.registerStat(STAT_MAGIC);
		MineStats.registerStat(STAT_POWER);
		MineStats.registerStat(STAT_STRENGTH);
		MineStats.registerStat(STAT_STAMINA);
		MineStats.registerStat(STAT_SPIRIT);
	}
}
