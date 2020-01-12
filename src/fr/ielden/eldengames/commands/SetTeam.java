package fr.ielden.eldengames.commands;

import fr.ielden.eldengames.EldenGames;
import fr.ielden.eldengames.game.FKPlayer;
import fr.ielden.eldengames.game.FKTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTeam implements CommandExecutor {
    EldenGames main;

    public SetTeam(EldenGames main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "Invalid usage: /setteam {orange | vert | none} [player]");
            return false;
        }
        FKTeam team;
        if (args[0].equals("vert"))
            team = this.main.getGame().team_green;
        else if (args[0].equals("orange"))
            team = this.main.getGame().team_orange;
        else if (args[0].equals("none"))
            team = this.main.getGame().team_none;
        else {
            commandSender.sendMessage(ChatColor.RED + "Invalid team: /setteam {orange | vert | none} [player]");
            return false;
        }
        FKPlayer player;
        if (args.length > 2) {
            Player pl = Bukkit.getServer().getPlayer(args[1]);
            if (pl == null) {
                commandSender.sendMessage(ChatColor.RED + "¨Player not found");
                return false;
            }
            player = this.main.getGame().getFKPlayer(pl);
            commandSender.sendMessage(ChatColor.GREEN + "team set");
        }
        else {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "Invalid player, please specify: /setteam {orange | vert | none} [player]");
                return false;
            }
            player = this.main.getGame().getFKPlayer((Player) commandSender);
        }
        player.set_team(team);
        player.mc_player.sendMessage(ChatColor.YELLOW + "Vous êtes à présent dans l'équipe " + team.toString());
        return true;
    }
}
