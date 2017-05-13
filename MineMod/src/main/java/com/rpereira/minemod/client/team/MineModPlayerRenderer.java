package com.rpereira.minemod.client.team;

import org.lwjgl.opengl.GL11;

import com.rpereira.mineclass.MineClass;
import com.rpereira.mineclass.common.classes.EntityClassInstance;
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
		GuiUtils.drawEntity(x, y + 12, 8, 0, 0, player);

		// render the player name
		String playername = player.getCommandSenderName();
		int color = Integer.MAX_VALUE;
		font.drawStringWithShadow(playername, x + 34 - font.getStringWidth(playername) / 2, y, color);

		// health bar
		int boundTexture = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
		int w = 65;
		int h = 13;

		float ratio = player.getHealth() / player.getMaxHealth();
		mc.getTextureManager().bindTexture(BARS);
		GuiUtils.drawTexturedModalRect(x, y + 10, 0, 0, w, h, 0);
		GuiUtils.drawTexturedModalRect(x, y + 10, w, 0, (int) (w * ratio), h, 0);

		// resource bar
		EntityClassInstance entityClassInstance = MineClass.proxyClient().getEntityClassInstance(player);
		if (entityClassInstance != null) {
			
			int Y = y + 10 + h + 1;
			Stats stats = entityClassInstance.getStats();
			StatResource statResource = entityClassInstance.getEntityClass().getResourceStat();
			float stat = stats.get(statResource);
			float maxstat = stats.get(statResource.getStatmax());
			ratio = stat / maxstat;
			GuiUtils.drawTexturedModalRect(x, Y, 0, 28, w, 13, 0);
			GuiUtils.drawTexturedModalRect(x, Y, w * 2, 84, (int) (w * ratio), h, 0);

			// resource string
			String resource = stat + "/" + maxstat;
			font.drawStringWithShadow(resource, x + 34 - font.getStringWidth(resource) / 2, Y + 3, Integer.MAX_VALUE);
		}

		// health string
		String health = player.getHealth() + "/" + player.getMaxHealth();
		font.drawStringWithShadow(health, x + 34 - font.getStringWidth(health) / 2, y + 13, Integer.MAX_VALUE);

		return (50);

	}

}
