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
import org.bukkit.util.StringUtil;

import java.util.ArrayList;

public class Command_ManhuntQuickstart implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Add given player names to runners
        if (args.length > 0) {
            ArrayList<Player> runners = new ArrayList<>();
            // Verify all names are valid
            for (String name : args) {
                Player curr = Bukkit.getPlayer(name);
                if (curr == null) {
                    sender.sendMessage(StringHelper.colorize(BroadcastHelper.BroadcastPrefix + " &cError: " + name + " is not a valid name"));
                    return false;
                }
                runners.add(curr);
            }
            HunterHelper.Runners.clear();
            HunterHelper.Hunters.clear();
            for (Player p : runners) {
                HunterHelper.addRunner(p.getUniqueId());
            }
            for(Player p:Bukkit.getOnlinePlayers()) {
                if(!HunterHelper.Runners.contains(p.getUniqueId())) {
                    HunterHelper.addHunter(p.getUniqueId());
                }
            }
        } else {
            sender.sendMessage(StringHelper.colorize(BroadcastHelper.BroadcastPrefix + " &cError: Must provide at least 1 name"));
            return false;
        }
        PregameHelper.startRound(30);
        BroadcastHelper.BroadcastRunners();
        HunterHelper.giveAllHuntersACompass();
        return true;
    }
}
