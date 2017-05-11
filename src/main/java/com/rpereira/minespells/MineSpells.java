package com.rpereira.minespells;

import java.util.HashMap;

import com.rpereira.minespells.common.ASpell;
import com.rpereira.minespells.common.MineSpellProxy;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = MineSpells.MODID, name = MineSpells.NAME, version = MineSpells.VERSION)
public class MineSpells {
	public static final String MODID = "minespells";
	public static final String NAME = "MineSpells";
	public static final String VERSION = "1.0";

	public static final HashMap<Integer, ASpell> SPELLS = new HashMap<Integer, ASpell>();

	private static int nextID = 0;

	@SidedProxy(clientSide = "com.rpereira.minespells.client.MineSpellProxyClient", serverSide = "com.rpereira.minespells.common.MineSpellProxy")
	public static MineSpellProxy proxy;

	@EventHandler
	public void preInit(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "PreInit");
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Logger.get().log(Logger.Level.FINE, "Init");
		proxy.init();
	}

	/** register a new entity spell */
	public static final void registerSpell(ASpell spell) {
		SPELLS.put(spell.getID(), spell);
	}

	/** get a uid for this spell */
	public static final int getNextSpellID() {
		return (nextID++);
	}

	/** get a spell by it id */
	public static final ASpell getSpellByID(int id) {
		return (SPELLS.get(id));
	}
}
