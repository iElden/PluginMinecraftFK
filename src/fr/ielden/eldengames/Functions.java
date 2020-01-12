package fr.ielden.eldengames;

import org.bukkit.Location;
import org.bukkit.Material;

import static java.lang.Math.abs;

public class Functions {
    public static void create_domination_point(Location point_center) {
        for (int j = -4; j <= 4; j++) {
            for (int i = -4; i <= 4; i++) {
                if (abs(i) + abs(j) >= 7)
                    continue;
                Location l = new Location(point_center.getWorld(), point_center.getX() + i, point_center.getY(), point_center.getZ() + j);
                l.getBlock().setType(Material.WOOL);
            }
        }
        for (int i = 0; i > -2; i--) {
            Location l = new Location(point_center.getWorld(), point_center.getX(), point_center.getY() + i, point_center.getZ());
            l.getBlock().setType(Material.STAINED_GLASS);
        }
        Location beacon_loc = new Location(point_center.getWorld(), point_center.getX(), point_center.getY() - 2, point_center.getZ());
        beacon_loc.getBlock().setType(Material.BEACON);
        for (int j = -1; j <= 1; j++) {
            for (int i = -1; i <= 1; i++) {
                Location l = new Location(point_center.getWorld(), point_center.getX() + i, point_center.getY() -3, point_center.getZ() + j);
                l.getBlock().setType(Material.EMERALD_BLOCK);
            }
        }
    }
}
