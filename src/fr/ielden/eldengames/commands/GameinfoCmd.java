package fr.ielden.eldengames.commands;

import fr.ielden.eldengames.EldenGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameinfoCmd implements CommandExecutor {

    EldenGames main;

    public GameinfoCmd(EldenGames main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(main.getGame().toString());
        return true;
    }
}