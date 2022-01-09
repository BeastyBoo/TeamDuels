package com.github.beastyboo.modduel.team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class TeamFactory {

    private final TeamTracker teamTracker;

    TeamFactory(TeamTracker teamTracker) {
        this.teamTracker = teamTracker;
    }

    TeamData createTeam(Player player, String name) {

        if(teamTracker.getTeam(name) != null) {
            player.sendMessage(ChatColor.RED + "Team " + name + " already exist.");
            return null;
        }

        UUID uuid = player.getUniqueId();

        if(teamTracker.getTeam(uuid) != null) {
            player.sendMessage(ChatColor.RED + "You're already in a team.");
            return null;
        }

        TeamData teamData =  new TeamData(name, uuid);
        teamData.addMember(uuid);

        player.sendMessage(ChatColor.GREEN + "Team created!");
        return teamData;
    }

}
