package com.michielo.wands;

import com.michielo.enums.Wands;
import com.michielo.spells.impl.PoisonSpark;
import com.michielo.spells.Spell;
import com.michielo.spells.impl.PoisonWave;
import com.michielo.util.CircularListNavigator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WitchWand implements Wand {

    private final Wands type;
    private final ItemStack item;

    private final CircularListNavigator<Spell> navigator;

    public WitchWand() {
        this.type = Wands.WitchWand;

        ItemStack i = new ItemStack(Material.SPIDER_EYE, 1);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName("WitchWand");
        List<String> lm = new ArrayList<>(Arrays.asList("Witchwand", "", "#53266"));
        m.setLore(lm);
        i.setItemMeta(m);
        this.item = i;

        List<Spell> spells = new ArrayList<>(Arrays.asList(
                new PoisonSpark(3, 5, 50, 7),
                new PoisonWave(3, 3, 7)
        ));
        navigator = new CircularListNavigator<>(spells);
    }

    @Override
    public void getWand(Player player) {
        if (!player.hasPermission("wands." + type.name().toLowerCase())) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return;
        }
        player.getInventory().addItem(item);
    }

    @Override
    public void use(Player player) {
        if (!player.hasPermission("wands." + type.name().toLowerCase())) {
            player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
            return;
        }
        navigator.getCurrent().attemptUse(player);
    }

    @Override
    public void nextSpell(Player player, boolean previous) {
        if (!player.hasPermission("wands." + type.name().toLowerCase())) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return;
        }
        if (previous) {
            navigator.moveToPrevious();
        } else {
            navigator.moveToNext();
        }
        player.sendMessage(ChatColor.GRAY + "You are now using: " + navigator.getCurrent().getName());
    }

    public Wands getType() {
        return Wands.WitchWand;
    }

}
