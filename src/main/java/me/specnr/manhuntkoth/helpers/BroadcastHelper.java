package me.specnr.manhuntkoth.helpers;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class BroadcastHelper {
    public static String BroadcastPrefix = StringHelper.colorize("&r[&aManhunt&r] ");

    public static void Broadcast(String msg) {
        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(BroadcastPrefix + StringHelper.colorize(msg)));
    }

    public static String GetRunnerString() {
        String msg = "There are no runners currently";
        if (HunterHelper.Runners.size() > 0) {
            ArrayList<String> names = new ArrayList<>();
            for (UUID runner : HunterHelper.Runners) {
                names.add(Objects.requireNonNull(Bukkit.getPlayer(runner)).getName());
            }
            msg = StringHelper.colorize("Runners: &b&l&o" + String.join("&r, &b&l&o", names));
        }
        return msg;
    }

    public static void BroadcastRunners() {
        if (HunterHelper.Runners.size() > 0) {
            Broadcast(GetRunnerString());
        } else {
            Broadcast("There must be more than 1 player online");
        }
    }
}
