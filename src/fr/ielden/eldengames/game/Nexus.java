package fr.ielden.eldengames.game;

import org.bukkit.ChatColor;

public class Nexus {
    public int hp = 100;

    public Boolean damage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
        return (this.hp == 0);
    }

    public String string_hp() {
        ChatColor color = ChatColor.WHITE;
        if (this.hp <= 10)
            color = ChatColor.DARK_RED;
        else if (this.hp <= 40)
            color = ChatColor.RED;
        else if (this.hp <= 70)
            color = ChatColor.YELLOW;
        return color + String.valueOf(this.hp);
    }
}
