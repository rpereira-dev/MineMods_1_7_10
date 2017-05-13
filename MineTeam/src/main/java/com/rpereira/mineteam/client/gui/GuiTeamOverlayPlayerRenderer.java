package com.rpereira.mineteam.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/** the renderer for a player GUIS in a team overlay guis */
public abstract class GuiTeamOverlayPlayerRenderer {

	/** do the renderer */
	public abstract int render(World world, EntityPlayer player, FontRenderer font, int x, int y);
}