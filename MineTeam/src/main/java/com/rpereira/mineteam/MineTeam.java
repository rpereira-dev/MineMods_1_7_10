package com.rpereira.mineteam;

import java.util.ArrayList;

import com.rpereira.mineteam.client.MineTeamProxyClient;
import com.rpereira.mineteam.client.gui.GuiTeamOverlay;
import com.rpereira.mineteam.common.MineTeamProxy;
import com.rpereira.mineutils.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

@Mod(modid = MineTeam.MODID, name = MineTeam.NAME, version = MineTeam.VERSION)
public class MineTeam {
	public static final String MODID = "mineteam";
	public static final String NAME = "MineTeam";
	public static final String VERSION = "1.0";

	@SidedProxy(clientSide = "com.rpereira.mineteam.client.MineTeamProxyClient", serverSide = "com.rpereira.mineteam.common.MineTeamProxy")
	private static MineTeamProxy proxy;

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

	@SideOnly(Side.CLIENT)
	public static final GuiTeamOverlay guiTeamOverlay() {
		return (MineTeamProxyClient.guiTeamOverlay());
	}

	public static EntityPlayer[] getTeamMembers(EntityPlayer player) {
		ScorePlayerTeam team = player.getWorldScoreboard().getPlayersTeam(player.getCommandSenderName());
		if (team == null) {
			return (new EntityPlayer[0]);
		}
		ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>(team.getMembershipCollection().size());
		for (Object obj : team.getMembershipCollection()) {
			EntityPlayer otherPlayer = player.getEntityWorld().getPlayerEntityByName(obj.toString());
			if (otherPlayer != null) {
				players.add(otherPlayer);
			}
		}
		return (players.toArray(new EntityPlayer[players.size()]));
	}

	public static String getTeamPrefix(EntityPlayer player) {
		ScorePlayerTeam team = player.getWorldScoreboard().getPlayersTeam(player.getCommandSenderName());
		return (team == null ? null : team.getColorPrefix());
	}
}
