package com.rpereira.mineteam.common.packets.client;

import com.rpereira.mineteam.client.gui.GuiInvitation;
import com.rpereira.mineutils.PacketUtils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketTeamInvitation implements IMessage {

	public String inviter;
	public String teamName;
	public String token;

	public PacketTeamInvitation() {
		this("", "", "");
	}

	public PacketTeamInvitation(String inviter, String teamName, String token) {
		this.inviter = inviter;
		this.teamName = teamName;
		this.token = token;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.inviter = PacketUtils.readString(buf);
		this.teamName = PacketUtils.readString(buf);
		this.token = PacketUtils.readString(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketUtils.writeString(buf, this.inviter);
		PacketUtils.writeString(buf, this.teamName);
		PacketUtils.writeString(buf, this.token);
	}

	@SideOnly(Side.CLIENT)
	public static class Handler implements IMessageHandler<PacketTeamInvitation, IMessage> {
		@Override
		public IMessage onMessage(PacketTeamInvitation message, MessageContext ctx) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiInvitation(message));
			return null;
		}
	}
}