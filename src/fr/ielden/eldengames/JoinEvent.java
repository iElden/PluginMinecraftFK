package fr.ielden.eldengames;

import fr.ielden.eldengames.enumeration.EnumFKTeam;
import fr.ielden.eldengames.enumeration.State;
import fr.ielden.eldengames.game.FKPlayer;
import fr.ielden.eldengames.game.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    EldenGames main;

    public JoinEvent(EldenGames main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Game game = this.main.game;
        game.registerPlayer(event.getPlayer());
    }
}