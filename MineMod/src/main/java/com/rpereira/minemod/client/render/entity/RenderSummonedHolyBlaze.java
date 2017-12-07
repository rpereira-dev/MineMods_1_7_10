package com.rpereira.minemod.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSummonedHolyBlaze extends RenderLiving {

	public static final ResourceLocation TEXTURES = new ResourceLocation("minemod:textures/entity/holyblaze/1.png");

	public RenderSummonedHolyBlaze() {
		super(new ModelBlaze(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return (TEXTURES);
	}

}