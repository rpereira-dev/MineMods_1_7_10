package com.rpereira.mineteam.client.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class GuiTeamOverlay {

	/** the minecraft */
	public Minecraft mc;

	/** the renderer for a player GUIS in a team overlay guis */
	public abstract class Renderer {

		/** render the team name */
		public abstract int renderTeamName(ScorePlayerTeam team, FontRenderer font, int offx, int offy);

		/** do the renderer */
		public abstract int renderPlayer(World world, EntityPlayer player, String playername, FontRenderer font, int x,
				int y);
	}

	public class DefaultRenderer extends Renderer {

		@Override
		public int renderTeamName(ScorePlayerTeam team, FontRenderer font, int offx, int offy) {
			int color = Integer.MAX_VALUE;
			String prefix = team.getColorPrefix();
			if (prefix != null) {
				font.drawStringWithShadow(prefix, offx + 30 - font.getStringWidth(prefix) / 2, offy, color);
				return (14);
			}
			return (0);
		}

		@Override
		public int renderPlayer(World world, EntityPlayer player, String playername, FontRenderer font, int x, int y) {

			int color = Integer.MAX_VALUE;
			font.drawStringWithShadow(playername, 30 + x - font.getStringWidth(playername) / 2, y, color);

			if (player != null) {
				String str = "X: " + (int) player.posX + " Y:" + (int) player.posY + " Z:" + (int) player.posZ;
				font.drawStringWithShadow(str, 30 + x - font.getStringWidth(str) / 2, y + 10, Integer.MAX_VALUE);

				drawEntity(x - 4, y + 10, 8, 0, 0, player);
			}
			return (30);
		}
	}

	private Renderer renderer = new DefaultRenderer();

	public static final ResourceLocation CHARGE_BARRE = new ResourceLocation("magiccrusade:textures/gui/ChargeGUI.png");

	public GuiTeamOverlay(Minecraft m) {
		this.mc = m;
	}

	/** draw an entity */
	public static void drawEntity(int posx, int posy, int scalex, float scaley, float roty, EntityLivingBase entity) {
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) posx, (float) posy, 50.0F);
		GL11.glScalef((float) (-scalex), (float) scalex, (float) scalex);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f2 = entity.renderYawOffset;
		float f3 = entity.rotationYaw;
		float f4 = entity.rotationPitch;
		float f5 = entity.prevRotationYawHead;
		float f6 = entity.rotationYawHead;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan((double) (roty / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		entity.renderYawOffset = (float) Math.atan((double) (scaley / 40.0F)) * 20.0F;
		entity.rotationYaw = (float) Math.atan((double) (scaley / 40.0F)) * 40.0F;
		entity.rotationPitch = -((float) Math.atan((double) (roty / 40.0F))) * 20.0F;
		entity.rotationYawHead = entity.rotationYaw;
		entity.prevRotationYawHead = entity.rotationYaw;
		GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		entity.renderYawOffset = f2;
		entity.rotationYaw = f3;
		entity.rotationPitch = f4;
		entity.prevRotationYawHead = f5;
		entity.rotationYawHead = f6;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onGuiIngameRender(RenderGameOverlayEvent.Post event) {
		if (!this.mc.gameSettings.showDebugInfo) {

			EntityPlayer player = this.mc.thePlayer;
			FontRenderer font = this.mc.fontRenderer;
			World world = this.mc.theWorld;

			if (player.getTeam() != null) {
				this.mc.mcProfiler.startSection("group");

				ScorePlayerTeam team = player.getWorldScoreboard().getPlayersTeam(player.getCommandSenderName());
				int offx = 18;
				int offy = 8;

				offy += this.renderer.renderTeamName(team, font, offx, offy);

				for (Object obj : team.getMembershipCollection()) {
					String playername = obj.toString();
					EntityPlayer nextplayer = world.getPlayerEntityByName(playername);
					offy += this.renderer.renderPlayer(world, nextplayer, playername, font, offx, offy);
				}

				this.mc.mcProfiler.endSection();
			}
		}
	}

}