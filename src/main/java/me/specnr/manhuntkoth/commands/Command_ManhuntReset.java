package me.specnr.manhuntkoth.commands;

import me.specnr.manhuntkoth.helpers.BroadcastHelper;
import me.specnr.manhuntkoth.helpers.HunterHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_ManhuntReset implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Reset all runners
        HunterHelper.Runners.clear();
        HunterHelper.Hunters.clear();
        BroadcastHelper.Broadcast("&aAll hunters and runners have been reset!");
        return false;
    }
}
