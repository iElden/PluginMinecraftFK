package fr.ielden.eldengames.game;

import org.bukkit.scheduler.BukkitRunnable;

public class GameLoop extends BukkitRunnable {

    Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        game.half_tick = !game.half_tick;
        if (!game.half_tick)
            game.timer++;
        game.timer_str = game.timer / 60 + " : " + game.timer % 60;
        for (FKPlayer player : game.players) {
            player.display_scoreboard();
        }
    }
}
