package fr.ielden.eldengames;

import fr.ielden.eldengames.enumeration.EnumFKTeam;
import fr.ielden.eldengames.enumeration.State;
import fr.ielden.eldengames.game.FKPlayer;
import fr.ielden.eldengames.game.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    EldenGames main;

    public DeathEvent(EldenGames main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Game game = this.main.game;
        if (game.state == State.INGAME) {
            FKPlayer player = game.getFKPlayer(event.getEntity());
            if (player.get_team().is(EnumFKTeam.NONE))
                return;
            player.onDeath(event);
        }
    }
}
