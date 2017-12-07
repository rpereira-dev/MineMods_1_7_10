package com.rpereira.minemod.client.team;

import org.lwjgl.opengl.GL11;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
import com.rpereira.mineclass.common.stats.MineClassStats;
import com.rpereira.mineclass.common.stats.StatResource;
import com.rpereira.minestats.common.Stats;
import com.rpereira.mineteam.client.gui.GuiTeamOverlayPlayerRenderer;
import com.rpereira.mineutils.GuiUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MineModPlayerRenderer extends GuiTeamOverlayPlayerRenderer {

	public static final ResourceLocation BARS = new ResourceLocation("minemod:textures/gui/bars.png");

	@Override
	public int render(World world, EntityPlayer player, FontRenderer font, int x, int y) {

		Minecraft mc = Minecraft.getMinecraft();

		// render the player
		GuiUtils.drawEntity(x, y + 20, 8, 0, 0, player);

		// render the player name
		String playername = player.getCommandSenderName();
		EntityClassInstance entityClassInstance = MineClass.proxyClient().getEntityClassInstance(player);
		int color = Integer.MAX_VALUE;
		font.drawStringWithShadow(playername, x + 10, y + 4, color);

		String infos = "Lvl. 18";
		font.drawStringWithShadow(infos, x + 10, y + 14, color);

		// health bar
		int boundTexture = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
		int w = 65;
		int h = 13;

		float ratio = player.getHealth() / player.getMaxHealth();
		mc.getTextureManager().bindTexture(BARS);
		GuiUtils.drawTexturedModalRect(x, y + 24, 0, 0, w, h, 0);
		GuiUtils.drawTexturedModalRect(x, y + 24, w, 0, (int) (w * ratio), h, 0);

		// resource bar
		if (entityClassInstance != null) {

			int u1, u2, v1, v2;

			StatResource statres = entityClassInstance.getEntityClass().getResourceStat();
			if (statres == MineClassStats.STAT_MANA) {
				u1 = 0;
				v1 = 0;
				u2 = 0;
				v2 = 98;
			} else if (statres == MineClassStats.STAT_RAGE) {
				u1 = 0;
				v1 = 14;
				u2 = 65;
				v2 = 70;
			} else {
				u1 = 0;
				v1 = 28;
				u2 = 130;
				v2 = 84;
			}

			Stats stats = entityClassInstance.getStats();

			if (stats != null) {
				float res = entityClassInstance.getResource();
				float maxres = entityClassInstance.getMaxResource();
				ratio = res / maxres;
				GuiUtils.drawTexturedModalRect(x, y + 38, u1, v1, w, h, 0);
				GuiUtils.drawTexturedModalRect(x, y + 38, u2, v2, (int) (w * ratio), h, 0);
				// resource string
				String resource = (int)res + "/" + (int)maxres;
				font.drawStringWithShadow(resource, x + 34 - font.getStringWidth(resource) / 2, y + 41, color);
			}
		}

		// health string
		String health = player.getHealth() + "/" + player.getMaxHealth();
		font.drawString(health, x + 34 - font.getStringWidth(health) / 2, y + 27, color);

		return (50);
	}

}
