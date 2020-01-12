package fr.ielden.eldengames.game;

import fr.ielden.eldengames.utils.RespawnTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class FKPlayer {
    Game game;
    public Player mc_player;

    FKTeam team = null;
    public Boolean is_death = false;
    int death_count = 0;

    public FKPlayer(Game game, Player mc_player) {
        this.game = game;
        this.mc_player = mc_player;
        set_team(game.team_none);
    }

    public FKTeam get_team() {
        return team;
    }

    public void set_team(FKTeam team) {
        if (!(this.team == null))
            this.team.players.remove(this);
        this.team = team;
        team.players.add(this);
    }

    public void display_scoreboard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("playersb", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.BOLD + game.main.getConfig().getString("game_name"));

        List<Score> scores = new ArrayList<Score>();
        scores.add(objective.getScore(" "));
        scores.add(objective.getScore("Equipe : " + this.team.toString()));
        scores.add(objective.getScore("  "));
        scores.add(objective.getScore(ChatColor.DARK_GREEN + "Nexus: " + this.game.green_nexus.string_hp()));
        scores.add(objective.getScore(ChatColor.GOLD + "Nexus: " + this.game.orange_nexus.string_hp()));
        scores.add(objective.getScore("   "));
        scores.add(objective.getScore(ChatColor.RED + game.timer_str));

        int i = 0;
        for (Score score: scores) {
            score.setScore(--i);
        }
        this.mc_player.setScoreboard(scoreboard);
    }

    public void onDeath(PlayerDeathEvent event) {
        this.is_death = true;
        if (game.sudden_death) {
            Location death_loc = event.getEntity().getLocation();
            death_loc.setY(death_loc.getY() + 1);
            this.mc_player.setGameMode(GameMode.SPECTATOR);
            mc_player.sendTitle("§4Vous êtes mort", "§cVous ne pouvez pas respawn en mort subite !");
        }
        else {
            this.team.nexus.damage(1);
            event.setDeathMessage(event.getDeathMessage() + team.chat_color + "(-1 Nexus HP)");
            RespawnTimer respawn_timer = new RespawnTimer(this, 15, team.spawn);
            respawn_timer.runTaskTimer(this.game.main, 0, 20);
        }
        mc_player.spigot().respawn();
    }
}
