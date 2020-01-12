package fr.ielden.eldengames.commands;

import fr.ielden.eldengames.EldenGames;
import fr.ielden.eldengames.utils.CountDown;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCmd implements CommandExecutor {

    EldenGames main;

    public StartCmd(EldenGames main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        CountDown cd = new CountDown(10, main.getGame());
        cd.runTaskTimer(this.main, 0, 20);
        return true;
    }
}
