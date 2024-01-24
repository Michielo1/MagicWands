package com.michielo.bukkit.listeners;

import com.michielo.WandManager;
import com.michielo.wands.Wand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack i = e.getItem();
        Action a = e.getAction();

        if (i == null) return;
        if (i.getItemMeta() == null) return;
        if (i.getItemMeta().getLore() == null) return;

        Wand wand = WandManager.getInstance().getWand(e.getPlayer(), i.getItemMeta().getLore());
        if (wand == null) return;

        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            boolean previous = false;
            if (e.getPlayer().isSneaking()) previous = true;
            wand.nextSpell(e.getPlayer(), previous);
            return;
        }

        if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) {
            wand.use(e.getPlayer());
        }
    }

}
