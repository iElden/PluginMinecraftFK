package fr.ielden.eldengames.game;

import fr.ielden.eldengames.enumeration.EnumFKTeam;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class FKTeam {

    EnumFKTeam team;
    public Nexus nexus;
    public List<FKPlayer> players = new ArrayList<FKPlayer>();
    public Location spawn;
    public ChatColor chat_color;

    public FKTeam(EnumFKTeam team, Nexus nexus, Location spawn, ChatColor chatcolor) {
        this.team = team;
        this.nexus = nexus;
        this.chat_color = chatcolor;
        this.spawn = spawn;
    }

    public Boolean is(EnumFKTeam team) {
        return this.team == team;
    }

    public String toString() {
        switch (team) {
            case GREEN:
                return ChatColor.GREEN + "Vert";
            case ORANGE:
                return ChatColor.GOLD + "Orange";
            case NONE:
                return ChatColor.GRAY + "Spectateur";
        }
        return "???";
    }

    public List<String> getPlayerNames() {
        List<String> ls = new ArrayList<>();
        for (FKPlayer pl: this.players) {
            ls.add(pl.mc_player.getName());
        }
        return ls;
    }
}
