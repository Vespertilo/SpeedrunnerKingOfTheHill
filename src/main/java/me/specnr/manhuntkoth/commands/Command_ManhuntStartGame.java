package me.specnr.manhuntkoth.commands;

import me.specnr.manhuntkoth.helpers.BroadcastHelper;
import me.specnr.manhuntkoth.helpers.HunterHelper;
import me.specnr.manhuntkoth.helpers.PregameHelper;
import me.specnr.manhuntkoth.helpers.StringHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_ManhuntStartGame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Add given player names to hunters
        if (args.length > 0) {
            int i;
            try  {
               i = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {
                sender.sendMessage(StringHelper.colorize("&cPlease input a valid headstart time!"));
                return false;
            }
            if(HunterHelper.Runners.size()>0 && HunterHelper.Hunters.size()>0) {
                PregameHelper.startRound(i);
            }

        } else {
            sender.sendMessage(StringHelper.colorize(BroadcastHelper.BroadcastPrefix + "&cError: Must provide at least 1 name"));
            return false;
        }
        return true;
    }
}