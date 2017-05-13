package com.rpereira.mineteam.common.packets.server;

import java.util.HashMap;

import com.rpereira.mineteam.common.packets.InvitationToken;
import com.rpereira.mineteam.common.packets.PacketServer;
import com.rpereira.mineutils.PacketUtils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

public class PacketTeamInvitePlayer implements IMessage {

	/** the name of the invited player */
	public String player;

	public PacketTeamInvitePlayer() {
		this("");
	}

	public PacketTeamInvitePlayer(String playerName) {
		this.player = playerName;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.player = PacketUtils.readString(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketUtils.writeString(buf, this.player);
	}

	public static class Handler implements IMessageHandler<PacketTeamInvitePlayer, IMessage> {

		public static final HashMap<String, InvitationToken> TOKENS = new HashMap<String, InvitationToken>();

		@Override
		public IMessage onMessage(PacketTeamInvitePlayer message, MessageContext ctx) {

			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			EntityPlayerMP invitedPlayer = (EntityPlayerMP) player.worldObj.getPlayerEntityByName(message.player);

			if (invitedPlayer == null) {
				player.addChatComponentMessage(new ChatComponentText("This player isn't logged in."));
			} else if (invitedPlayer != null && invitedPlayer.getTeam() != null) {
				player.addChatComponentMessage(new ChatComponentText("This player already has a team"));
			} else { // invite a new player
				PacketServer.instance().invitePlayer(player, invitedPlayer);
			}

			return null;
		}
	}
}