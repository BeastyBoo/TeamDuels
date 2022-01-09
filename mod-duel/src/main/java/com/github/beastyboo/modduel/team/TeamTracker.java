package com.github.beastyboo.modduel.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeamTracker {

    private final Map<String, TeamData> teamInMemoryByName;
    private final Map<UUID, TeamData> teamInMemoryByPlayer;

    TeamTracker() {
        teamInMemoryByName = new HashMap<>();
        teamInMemoryByPlayer = new HashMap<>();
    }

    void cacheNewTeam(TeamData teamData) {
        teamInMemoryByName.put(teamData.getName().toLowerCase(), teamData);
        teamInMemoryByPlayer.put(teamData.getOwner(), teamData);
    }

    void cacheMemberToTeam(TeamData teamData, UUID uuid) {
        teamInMemoryByPlayer.put(uuid, teamData);
    }

    void removeTeam(TeamData teamData) {
        for (UUID uuid : teamData.copiedSet()) {
            Player player = Bukkit.getPlayer(uuid);
            if(player != null) {
                player.sendMessage(ChatColor.RED + "Team disbanded.");
            }
            teamInMemoryByPlayer.remove(uuid, teamData);
        }
        teamInMemoryByName.remove(teamData.getName().toLowerCase(), teamData);
    }

    TeamData getTeam(String name) {
        return teamInMemoryByName.get(name.toLowerCase());
    }

    TeamData getTeam(UUID uuid) {
        return teamInMemoryByPlayer.get(uuid);
    }
}
