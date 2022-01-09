package com.github.beastyboo.modduel.team;

import com.github.beastyboo.modduel.HoneyDuelCore;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class TeamManager {

    //TODO: invite accept, leave Team.

    private final HoneyDuelCore core;
    private final TeamTracker tracker;
    private final TeamFactory factory;

    public TeamManager(HoneyDuelCore core) {
        this.core = core;
        tracker = new TeamTracker();
        factory = new TeamFactory(tracker);
    }

    public void createTeam(Player player, String name) {
        TeamData teamData = factory.createTeam(player, name);

        if(teamData == null) {
            return;
        }

        tracker.cacheNewTeam(teamData);
    }

    public void deleteTeam(Player player) {
        UUID uuid = player.getUniqueId();

        TeamData teamData = tracker.getTeam(uuid);

        if(teamData == null) {
            player.sendMessage(ChatColor.RED + "You're not in a team.");
            return;
        }

        if(!teamData.getOwner().equals(uuid)) {
            player.sendMessage(ChatColor.RED + "You're not the owner of this team.");
            return;
        }

        tracker.removeTeam(teamData);
    }

    public void sendInvite(Player player, Player receiver) {
        TeamData teamData = tracker.getTeam(player.getUniqueId());

        if(teamData == null) {
            player.sendMessage(ChatColor.RED + "You're not in a team.");
            return;
        }

        UUID receiverUUID = receiver.getUniqueId();
        if(teamData.isMember(receiverUUID)) {
            player.sendMessage(ChatColor.RED + receiver.getName() + " is already a member of this team.");
            return;
        }

        if(teamData.sendInvite(receiverUUID)) {
            receiver.sendMessage(ChatColor.GREEN + "You have been invited to " + ChatColor.GOLD + teamData.getName());
            player.sendMessage(ChatColor.GREEN + "Successfully invited " + receiver.getName());
        }
    }

    public void removeInvite(Player player, OfflinePlayer target) {
        if(target == null) {
            player.sendMessage(ChatColor.RED + "Player not found.");
            return;
        }

        TeamData teamData = tracker.getTeam(player.getUniqueId());

        if(teamData == null) {
            player.sendMessage(ChatColor.RED + "You're not in a team.");
            return;
        }

        UUID targetUUID = target.getUniqueId();

        if(teamData.isMember(targetUUID)) {
            player.sendMessage(ChatColor.RED + target.getName() + " is already a member of this team.");
            return;
        }

        if(!teamData.isInvited(targetUUID)) {
            player.sendMessage(ChatColor.RED + target.getName() + " is not invited to this team.");
            return;
        }

        if(teamData.removeInvite(targetUUID)) {
            player.sendMessage(ChatColor.GREEN + "Successfully removed invite to " + target.getName());
        }
    }

}
