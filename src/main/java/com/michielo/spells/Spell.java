package com.michielo.spells;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class Spell {

    private final long cooldown;
    private final String name;
    private long lastUsed;

    public Spell(String name, int cooldown) {
        this.cooldown = cooldown * 1000L; // converting cooldown seconds into ms
        this.name = name;
        this.lastUsed = 0;
    }

    public void attemptUse(Player player) {
        if (lastUsed + cooldown > System.currentTimeMillis()) {
            player.sendMessage(ChatColor.RED + "The cooldown is still active!");
            return;
        }
        use(player);
        lastUsed = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public abstract void use(Player player);

}
