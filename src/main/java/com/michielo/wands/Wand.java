package com.michielo.wands;

import com.michielo.enums.Wands;
import org.bukkit.entity.Player;

public interface Wand {

    void getWand(Player player);
    void use(Player player);
    void nextSpell(Player player, boolean previous);
    Wands getType();

}
