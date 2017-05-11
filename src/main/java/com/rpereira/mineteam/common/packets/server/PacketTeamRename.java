package com.rpereira.mineteam.common.packets.server;

import com.rpereira.mineteam.common.packets.PacketUtils;
import com.rpereira.mineutils.ChatColor;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;

public class PacketTeamRename implements IMessage {

	/** the color id */
	public int colorID;

	/** the prefix */
	public String prefix;

	public PacketTeamRename() {
		this("", ChatColor.YELLOW.ordinal());
	}

	public PacketTeamRename(String prefix, int colorID) {
		this.prefix = prefix;
		this.colorID = colorID;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.colorID = buf.readInt();
		this.prefix = PacketUtils.readString(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.colorID);
		PacketUtils.writeString(buf, this.prefix);
	}

	public static class Handler implements IMessageHandler<PacketTeamRename, IMessage> {
		@Override
		public IMessage onMessage(PacketTeamRename message, MessageContext ctx) {

			ChatColor color = ChatColor.getByID(message.colorID);
			if (color == null) {
				color = ChatColor.YELLOW;
			}

			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			ScorePlayerTeam team = player.getWorldScoreboard().getPlayersTeam(player.getCommandSenderName());
			team.setNamePrefix(color + "[" + message.prefix + "] ");
			for (Object obj : team.getMembershipCollection()) {
				EntityPlayer tmp = player.worldObj.getPlayerEntityByName(obj.toString());
				if (tmp != null) {
					tmp.addChatComponentMessage(new ChatComponentText("Your group prefix has been changed."));
				}
			}

			return null;
		}
	}
}