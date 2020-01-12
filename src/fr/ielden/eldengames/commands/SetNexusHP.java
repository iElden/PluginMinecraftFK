package fr.ielden.eldengames.commands;

import fr.ielden.eldengames.EldenGames;
import fr.ielden.eldengames.game.FKPlayer;
import fr.ielden.eldengames.game.FKTeam;
import fr.ielden.eldengames.game.Nexus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetNexusHP implements CommandExecutor {
    EldenGames main;

    public SetNexusHP(EldenGames main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length != 2) {
            commandSender.sendMessage(ChatColor.RED + "Invalid usage: /setteam {orange | vert | none} [player]");
            return false;
        }
        Nexus nexus;
        if (args[0].equals("vert"))
            nexus = this.main.getGame().team_green.nexus;
        else if (args[0].equals("orange"))
            nexus = this.main.getGame().team_orange.nexus;
        else {
            commandSender.sendMessage(ChatColor.RED + "Invalid nexus: /setnexushp {orange | vert} {nombre}");
            return false;
        }
        int hp;
        try {
            hp = Integer.parseInt(args[1]);
        }
        catch (Exception e) {
            commandSender.sendMessage(ChatColor.RED + "Invalid unmber: /setnexushp {orange | vert} {nombre}");
            return false;
        }
        nexus.hp = hp;
        Bukkit.broadcastMessage(commandSender.getName() + ChatColor.YELLOW + "a set le nombre de point de vie du nexus " + args[0] + " à " + nexus.string_hp());
        return true;
    }
}