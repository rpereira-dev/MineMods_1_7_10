package com.rpereira.minemod.client.render.entity;

import com.rpereira.minemod.common.entity.EntitySummonableZombie;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSummonedZombie extends RenderBiped {

	public static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			new ResourceLocation("minemod:textures/entity/zombie/1.png"),
			new ResourceLocation("minemod:textures/entity/zombie/2.png"),
			new ResourceLocation("minemod:textures/entity/zombie/3.png"),
			new ResourceLocation("minemod:textures/entity/zombie/4.png") };

	public RenderSummonedZombie() {
		super(new ModelBiped(), 0.5F, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return (TEXTURES[((EntitySummonableZombie) entity).getType() % TEXTURES.length]);
	}

}