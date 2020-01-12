package fr.ielden.eldengames.commands;

import fr.ielden.eldengames.EldenGames;
import fr.ielden.eldengames.Functions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCmd implements CommandExecutor {

    EldenGames main;

    public TestCmd(EldenGames main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        Functions.create_domination_point(new Location(Bukkit.getWorld("world"), -58, 85, 11));
        return true;
    }
}
