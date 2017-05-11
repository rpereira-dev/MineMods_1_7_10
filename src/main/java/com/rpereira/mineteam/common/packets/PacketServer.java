package com.rpereira.mineteam.common.packets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.rpereira.mineteam.common.packets.client.PacketTeamInvitation;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;

public class PacketServer {

	/** the maximum invitations a player can send before being refused */
	private static final int MAX_INVITATION_PER_PLAYER = 10;

	/** checks token only every 5 seconds only */
	private static final long TOKEN_UPDATE_THRESHOLD = 5 * 1000;

	/** token validity time: 1 minute */
	private static final long INVITATION_TOKEN_TIME_VALIDITY = 60 * 1000;

	private static PacketServer instance;

	/** the token hashmap */
	public final HashMap<String, InvitationToken> tokens = new HashMap<String, InvitationToken>();

	/** the invitation sent by players */
	public final HashMap<String, Integer> invitations = new HashMap<String, Integer>();

	private long lastCheck;

	public PacketServer() {
		instance = this;
		this.lastCheck = System.currentTimeMillis();
	}

	public void onServerTick() {

		long curr = System.currentTimeMillis();
		if (curr - this.lastCheck < TOKEN_UPDATE_THRESHOLD) {
			return;
		}
		this.lastCheck = curr;

		for (Iterator<Entry<String, InvitationToken>> it = this.tokens.entrySet().iterator(); it.hasNext();) {
			Entry<String, InvitationToken> entry = it.next();
			if (curr - entry.getValue().timestamp >= INVITATION_TOKEN_TIME_VALIDITY) {
				it.remove();
			}
		}
	}

	/** the first player invite the second one in his team */
	public void invitePlayer(EntityPlayerMP inviter, EntityPlayerMP invited) {
		String invitername = inviter.getCommandSenderName();
		Integer invits = this.invitations.get(invitername);
		int invitsCount = invits == null ? 0 : invits.intValue();
		if (invitsCount > MAX_INVITATION_PER_PLAYER) {
			inviter.addChatComponentMessage(new ChatComponentText(
					"You already sent to much invitations. Please wait a few minutes before resending."));
		} else {
			this.invitations.put(invitername, invitsCount + 1);
			String teamname = inviter.getTeam().getRegisteredName();
			String invitedname = invited.getCommandSenderName();
			inviter.addChatComponentMessage(new ChatComponentText("You have invited " + invitedname));
			InvitationToken token = new InvitationToken(inviter, invited, teamname);
			while (this.tokens.containsKey(token.newValue()))
				;
			this.tokens.put(token.value, token);
			IMessage packet = new PacketTeamInvitation(invitername, teamname, token.value);
			Packets.network.sendTo(packet, invited);
		}
	}

	public void playerAcceptInvitation(EntityPlayerMP invited, String tokenID) {
		InvitationToken token = this.tokens.get(tokenID);
		if (token == null) {
			String text = "Invalid token: invitation may have expired.";
			invited.addChatComponentMessage(new ChatComponentText(text));
		} else {
			if (invited != token.invited) {
				String text = "Wait what...? You're not invited there! CHEATER.";
				invited.addChatComponentMessage(new ChatComponentText(text));
			} else {
				this.tokens.remove(tokenID);
				Scoreboard scoreboard = token.invited.worldObj.getScoreboard();
				ScorePlayerTeam team = scoreboard.getTeam(token.teamName);
				if (team != null) {
					invited.worldObj.getScoreboard().func_151392_a(token.invited.getCommandSenderName(),
							token.teamName);
					for (Object obj : team.getMembershipCollection()) {
						EntityPlayer tmp = token.invited.worldObj.getPlayerEntityByName(obj.toString());
						tmp.addChatComponentMessage(
								new ChatComponentText(token.invited.getCommandSenderName() + " has joined the group."));
					}
				} else {
					invited.addChatComponentMessage(new ChatComponentText("This team no longer exists."));
				}
			}
		}
	}

	public void playerRefuseInvitation(String tokenID) {
		InvitationToken token = this.tokens.get(tokenID);
		if (token == null) {
			return;
		}
		if (token.inviter != null && token.invited != null) {
			String msg = token.invited.getCommandSenderName() + " refused your invitation.";
			token.inviter.addChatComponentMessage(new ChatComponentText(msg));
		}
		this.tokens.remove(tokenID);
	}

	public static PacketServer instance() {
		return (instance);
	}
}
