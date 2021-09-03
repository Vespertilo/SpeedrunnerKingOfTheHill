package me.specnr.manhuntkoth.commands;

import me.specnr.manhuntkoth.helpers.BroadcastHelper;
import me.specnr.manhuntkoth.helpers.HunterHelper;
import me.specnr.manhuntkoth.helpers.StringHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Command_ManhuntRunner implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Add given player names to runners
        if (args.length > 0) {
            //verify name is valid
            String name = args[0];
            Player curr = Bukkit.getPlayer(name);
            if (curr == null) {
                sender.sendMessage(StringHelper.colorize(BroadcastHelper.BroadcastPrefix + " &cError: " + name + " is not a valid name"));
                return false;
            }
            HunterHelper.addRunner(curr.getUniqueId());

        } else {
            sender.sendMessage(StringHelper.colorize(BroadcastHelper.BroadcastPrefix + " &cError: Must provide at least 1 name"));
            return false;
        }
        return true;
    }
}
