package com.rpereira.mineteam.client.gui;

import com.rpereira.mineteam.MineTeam;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class GuiTeamOverlay {

	/** the minecraft */
	private GuiTeamOverlayPlayerRenderer playerRenderer = new GuiTeamOverlayPlayerRendererDefault();
	private GuiTeamOverlayPlayerRenderer otherPlayerRenderer = playerRenderer;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onGuiIngameRender(RenderGameOverlayEvent.Chat event) {
		Minecraft mc = Minecraft.getMinecraft();
		if (!mc.gameSettings.showDebugInfo && mc.inGameHasFocus) {

			EntityPlayer player = mc.thePlayer;
			FontRenderer font = mc.fontRenderer;
			World world = mc.theWorld;

			mc.mcProfiler.startSection("team");

			int offx = 14;
			int offy = 0;

			if (player.getTeam() != null) {

				// render the player
				String prefix = MineTeam.getTeamPrefix(player);
				boolean hasPrefix = prefix != null && prefix.length() > 0;
				int dy = hasPrefix ? 14 : 0;

				if (this.playerRenderer != null) {
					dy += this.playerRenderer.render(world, player, font, offx, offy + dy);
				}

				EntityPlayer[] players = MineTeam.getTeamMembers(player);

				// render other players
				if (this.otherPlayerRenderer != null) {
					for (EntityPlayer nextplayer : players) {
						dy += this.otherPlayerRenderer.render(world, nextplayer, font, offx, offy + dy);
					}
				}

				// render team name
				int color = Integer.MAX_VALUE;
				if (prefix != null && prefix.length() > 0) {
					font.drawStringWithShadow(prefix, offx + 30 - font.getStringWidth(prefix) / 2, offy, color);
				}
			} else {
				// render the player
				if (this.playerRenderer != null) {
					offy += this.playerRenderer.render(world, player, font, offx, offy);
				}
			}
			mc.mcProfiler.endSection();

		}
	}

	public void setPlayerRender(GuiTeamOverlayPlayerRenderer playerRenderer) {
		this.playerRenderer = playerRenderer;
	}

	public void setOtherPlayerRender(GuiTeamOverlayPlayerRenderer otherPlayerRenderer) {
		this.otherPlayerRenderer = otherPlayerRenderer;
	}
}