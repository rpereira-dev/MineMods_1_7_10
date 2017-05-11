package com.rpereira.mineteam.common.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;

public class PacketTeamLeave implements IMessage {

	public PacketTeamLeave() {
		this("");
	}

	public PacketTeamLeave(String player) {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class Handler implements IMessageHandler<PacketTeamLeave, IMessage> {
		@Override
		public IMessage onMessage(PacketTeamLeave message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			ScorePlayerTeam team = player.getWorldScoreboard().getPlayersTeam(player.getCommandSenderName());
			Scoreboard scoreboard = player.getWorldScoreboard();
			scoreboard.removePlayerFromTeams(player.getCommandSenderName());
			if (team.getMembershipCollection().size() == 0) {
				player.getWorldScoreboard().removeTeam(team);
				player.addChatComponentMessage(new ChatComponentText("Your team was destroyed"));
			} else {
				for (Object obj : team.getMembershipCollection()) {
					EntityPlayer tmp = player.worldObj.getPlayerEntityByName(obj.toString());
					tmp.addChatComponentMessage(
							new ChatComponentText(player.getCommandSenderName() + " has left the team."));
				}
			}
			return null;
		}
	}
}