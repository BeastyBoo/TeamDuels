package com.github.beastyboo.modduel.team;

import com.github.beastyboo.team.Team;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public final class TeamData implements Team {

    private final String name;
    private final UUID owner;
    private final Set<UUID> members;
    private final Set<UUID> invites;

    TeamData(String name, UUID owner) {
        this.name = name;
        this.owner = owner;
        members = new HashSet<>();
        invites = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public UUID getOwner() {
        return owner;
    }

    public boolean isMember(UUID uuid) {
        return members.contains(uuid);
    }

    public boolean addMember(UUID uuid) {
        if(!members.contains(uuid)) {
            members.add(uuid);
            return true;
        }
        return false;
    }

    public boolean removeMember(UUID uuid) {
        if(members.contains(uuid)) {
            members.remove(uuid);
            return true;
        }
        return false;
    }

    public boolean isInvited(UUID uuid) {
        return invites.contains(uuid);
    }

    public boolean sendInvite(UUID uuid) {
        if(!invites.contains(uuid)) {
            invites.add(uuid);
            return true;
        }
        return false;
    }

    public boolean removeInvite(UUID uuid) {
        if(invites.contains(uuid)) {
            invites.remove(uuid);
            return true;
        }
        return false;
    }

    public Set<UUID> copiedSet() {
        return new HashSet<>(members);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamData teamData = (TeamData) o;
        return name.equals(teamData.name) && owner.equals(teamData.owner) && members.equals(teamData.members) && invites.equals(teamData.invites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner, members, invites);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", members=" + members +
                ", invites=" + invites +
                '}';
    }
}
