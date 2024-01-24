package com.michielo;

import com.michielo.enums.Wands;
import com.michielo.wands.Wand;
import com.michielo.wands.WitchWand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WandManager {

    /*
        Instance getter
     */
    private static WandManager wandManager;
    public static WandManager getInstance() {
        if (wandManager == null) wandManager = new WandManager();
        return wandManager;
    }

    /*
        Class creation
     */
    public WandManager() {
        List<String> witchwand = new ArrayList<>(Arrays.asList("Witchwand", "", "#53266"));
        loreToWand.put(witchwand, Wands.WitchWand);
    }

    /*
        Stuff
     */
    private HashMap<List<String>, Wands> loreToWand = new HashMap<>();
    private HashMap<Player, Wand> existingWands = new HashMap<>();

    public Wand getWand(Player player, List<String> lore) {

        Wands type = loreToWand.get(lore);
        if (type == null) return null;

        if (type == Wands.WitchWand) {
            if (existingWands.get(player) == null) {
                existingWands.put(player, new WitchWand());
                return getWand(player, lore);
            }
            return existingWands.get(player);
        }

        return null;
    }

}
