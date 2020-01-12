package fr.ielden.eldengames.game;

import fr.ielden.eldengames.EldenGames;
import fr.ielden.eldengames.enumeration.EnumFKTeam;
import fr.ielden.eldengames.enumeration.State;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;

public class Game {
    EldenGames main;
    public State state = State.WAITING;
    Nexus green_nexus = new Nexus();
    Nexus orange_nexus = new Nexus();
    public FKTeam team_none = new FKTeam(EnumFKTeam.NONE, null, new Location(Bukkit.getWorld("world"), 0.f, 128.f, 0.f), ChatColor.GRAY);
    public FKTeam team_green = new FKTeam(EnumFKTeam.GREEN, green_nexus, new Location(Bukkit.getWorld("world"), 15.f, 70.f, 15.f), ChatColor.GREEN);
    public FKTeam team_orange = new FKTeam(EnumFKTeam.ORANGE, orange_nexus, new Location(Bukkit.getWorld("world"), -15.f, 70.f, -15.f), ChatColor.GOLD);
    public Boolean sudden_death = false;
    public int timer = 0;
    public Boolean half_tick = false;
    public String timer_str = "0:00";

    List<FKPlayer> players = new ArrayList<FKPlayer>();

    public Game(EldenGames main) {
        this.main = main;
        for (Player pl: Bukkit.getOnlinePlayers())
            registerPlayer(pl);
    }

    public void registerPlayer(Player player) {
        if (this.getFKPlayer(player) != null)
            return;
        players.add( new FKPlayer(this, player));
    }

    public FKPlayer getFKPlayer(Player player) {
        for (FKPlayer pl: players) {
            if (player == pl.mc_player)
                return pl;
        }
        return null;
    }

    public void start() {
        this.state = State.INGAME;
        GameLoop game_loop = new GameLoop(this);
        for (FKPlayer player : players) {
            if (!player.team.is(EnumFKTeam.NONE)) {
                player.mc_player.setGameMode(GameMode.SURVIVAL);
                player.mc_player.teleport(player.team.spawn);
            }
        }
        for (FKPlayer player : team_none.players) {
            player.mc_player.setGameMode(GameMode.SPECTATOR);
            player.mc_player.teleport(player.team.spawn);
        }
        game_loop.runTaskTimer(this.main, 0, 10);
    }

    public String toString() {
        String state = "???";
        switch (this.state) {
            case WAITING:
                state = "WAITING";
                break;
            case INGAME:
                state = "INGAME";
                break;
            case ENDED:
                state = "ENDED";
                break;
        }
        String spectator = String.join(", ", team_none.getPlayerNames());
        String green = String.join(", ", team_green.getPlayerNames());
        String orange = String.join(", ", team_orange.getPlayerNames());
        return "State: " + state + "\nSpectator: " + spectator + "\nVert: " + green + "\nOrange: " + orange;
    }
}