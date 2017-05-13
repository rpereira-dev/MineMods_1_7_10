package com.rpereira.mineteam.client.gui;

import com.rpereira.mineutils.GuiUtils;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiTeamOverlayPlayerRendererDefault extends GuiTeamOverlayPlayerRenderer {

	@Override
	public int render(World world, EntityPlayer player, FontRenderer font, int x, int y) {

		GuiUtils.drawEntity(x, y + 10, 8, 0, 0, player);

		String playername = player.getCommandSenderName();
		int color = Integer.MAX_VALUE;
		font.drawStringWithShadow(playername, x + 34 - font.getStringWidth(playername) / 2, y, color);

		if (player != null) {
			String str = "X: " + (int) player.posX + " Y:" + (int) player.posY + " Z:" + (int) player.posZ;
			font.drawStringWithShadow(str, x + 34 - font.getStringWidth(str) / 2, y + 10, Integer.MAX_VALUE);
		}
		return (34);
	}
}
