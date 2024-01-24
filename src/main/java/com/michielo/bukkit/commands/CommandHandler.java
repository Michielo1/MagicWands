package com.michielo.bukkit.commands;

import com.michielo.wands.impl.WitchWand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command c, String label, String[] args) {
        if (args[0].equalsIgnoreCase("get") && args[1].equalsIgnoreCase("witchwand")) {
            WitchWand witchWand = new WitchWand();
            witchWand.getWand((Player) commandSender);
        }
        return true;
    }

}
