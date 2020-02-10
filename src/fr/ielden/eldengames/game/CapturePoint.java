package fr.ielden.eldengames.game;

import org.bukkit.Location;
import org.bukkit.Material;

import static java.lang.Math.abs;

public class CapturePoint {
    Game _game;
    int _score;
    Location center;

    public void capture(FKPlayer player) {
        int diff = 0;
        if (player.team == _game.team_green) {
            diff = +1;
        }
        else if (player.team == _game.team_orange) {
            diff = -1;
        }

    }

    public void create() {
        this.create_wools(Material.WOOL);
        this.create_glass(Material.STAINED_GLASS);
        this.create_beacon();
        this.create_emeralds();

    }

    public void create_wools(Material material) {
        for (int j = -4; j <= 4; j++) {
            for (int i = -4; i <= 4; i++) {
                if (abs(i) + abs(j) >= 7)
                    continue;
                Location l = new Location(this.center.getWorld(), this.center.getX() + i, this.center.getY(), this.center.getZ() + j);
                l.getBlock().setType(material);
            }
        }
    }

    public void create_glass(Material material) {
        for (int i = 0; i > -2; i--) {
            Location l = new Location(this.center.getWorld(), this.center.getX(), this.center.getY() + i, this.center.getZ());
            l.getBlock().setType(material);
        }
    }

    public void create_beacon() {
        Location beacon_loc = new Location(this.center.getWorld(), this.center.getX(), this.center.getY() - 2, this.center.getZ());
        beacon_loc.getBlock().setType(Material.BEACON);
    }

    public void create_emeralds() {
        for (int j = -1; j <= 1; j++) {
            for (int i = -1; i <= 1; i++) {
                Location l = new Location(this.center.getWorld(), this.center.getX() + i, this.center.getY() -3, this.center.getZ() + j);
                l.getBlock().setType(Material.EMERALD_BLOCK);
            }
        }
    }
}
