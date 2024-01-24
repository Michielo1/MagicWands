package com.michielo.util;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Eyes {

    public static Entity isLookingAtEntity(Player player) {
        Collection<Entity> entities = player.getNearbyEntities(50, 50, 50);
        for (Entity e : entities) {
            if (player.hasLineOfSight(e)) {
                return e;
            }
        }
        return null;
    }

}
