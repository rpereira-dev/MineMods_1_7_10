package com.rpereira.mineteam.common.packets.server;

import com.rpereira.mineteam.common.packets.PacketUtils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;

public class PacketTeamCreateTeam implements IMessage {

	/** team name and prefix */
	public String teamName;

	public PacketTeamCreateTeam() {
		this("");
	}

	public PacketTeamCreateTeam(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.teamName = PacketUtils.readString(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketUtils.writeString(buf, this.teamName);
	}

	public static class Handler implements IMessageHandler<PacketTeamCreateTeam, IMessage> {
		@Override
		public IMessage onMessage(PacketTeamCreateTeam message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			Scoreboard scoreboard = ctx.getServerHandler().playerEntity.worldObj.getScoreboard();
			ScorePlayerTeam team = scoreboard.getTeam(String.valueOf(message.teamName));

			if (team != null) {
				player.addChatComponentMessage(new ChatComponentText("A team with the same name already exist."));
			} else {
				scoreboard.createTeam(message.teamName);
				scoreboard.func_151392_a(player.getCommandSenderName(), message.teamName);
			}

			return null;

		}
	}
}