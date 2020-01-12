package fr.ielden.eldengames.utils;

import fr.ielden.eldengames.game.FKPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class RespawnTimer extends BukkitRunnable {

    Player player;
    FKPlayer fkplayer;
    int timer;
    Location target_location;
    Collection<? extends Player> online_players;


    public RespawnTimer(FKPlayer fkplayer, int timer, Location target_location) {
        this.timer = timer + 1;
        this.target_location = target_location;
        this.online_players = getOnlinePlayers();
        this.fkplayer = fkplayer;
        this.player = fkplayer.mc_player;
        at_start(this.player);
    }

    @Override
    public void run() {
        this.timer--;
        if (this.timer == 0) {
            this.at_end(this.player);
            cancel();
        }
        else {
            this.player.sendTitle("§4Vous êtes mort", "§6Respawn dans §c" + this.timer + " §6secondes");
            player.setFlying(true);
            player.setFlySpeed(0.0f);
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0, false, false), true);
            player.teleport(new Location(player.getWorld(), 0, -50, 0));
            player.setNoDamageTicks(40);
        }
    }

    private void at_start(Player player) {
        for (Player pl : this.online_players) {
            pl.hidePlayer(player);
        }
        player.setAllowFlight(true);
    }

    private void at_end(Player player) {
        for (Player pl : this.online_players) {
            pl.showPlayer(player);
        }
        this.player.sendTitle("§4Vous êtes mort", "§6Retour à la vie");
        this.player.resetTitle();
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 126, false, false), true);
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setSaturation(5.0f);
        player.teleport(this.target_location);
        player.setFlying(false);
        player.setFlySpeed(0.1f);
        player.setAllowFlight(false);
        fkplayer.is_death = false;
    }
}
