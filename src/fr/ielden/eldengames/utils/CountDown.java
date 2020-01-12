package fr.ielden.eldengames.utils;

import fr.ielden.eldengames.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class CountDown extends BukkitRunnable {

    private int timer;
    private String prefix = "§6[§cEldenGames§6] ";
    private Game game;

    public CountDown(int timer, Game game) {
        this.timer = timer;
        this.game = game;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
            Bukkit.broadcastMessage(this.prefix + "La partie commence dans §c" + this.timer + "§6 s");
        }
    }

    @Override
    public void run() {
        timer--;
        if (timer <= 0) {
            Bukkit.broadcastMessage(this.prefix + "La partie commence");
            cancel();
            game.start();
        }
        else if (timer <= 5) {
            Bukkit.broadcastMessage(this.prefix + "La partie commence dans §c" + this.timer + "§6 s");
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 2.0f);
            }
        }
    }
}
