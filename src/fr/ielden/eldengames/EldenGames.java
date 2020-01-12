package fr.ielden.eldengames;

import fr.ielden.eldengames.commands.GameinfoCmd;
import fr.ielden.eldengames.commands.SetTeam;
import fr.ielden.eldengames.commands.StartCmd;
import fr.ielden.eldengames.commands.TestCmd;
import fr.ielden.eldengames.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EldenGames extends JavaPlugin {

    Game game; ;

    @Override
    public void onEnable() {
        System.out.println("[Elden's Games] initialisation ...");
        saveDefaultConfig();
        this.game = new Game(this);
        getCommand("start").setExecutor(new StartCmd(this));
        getCommand("setteam").setExecutor(new SetTeam(this));
        getCommand("gameinfo").setExecutor(new GameinfoCmd(this));
        getCommand("test").setExecutor(new TestCmd(this));
        Bukkit.getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[Elden's Games] cleaning ...");
    }

    public Game getGame() {
        return this.game;
    }
}
