package me.specnr.manhuntkoth.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static org.bukkit.World.Environment.THE_END;

public class HunterHelper {
    public static Set<UUID> Runners = new HashSet<>();
    public static Set<UUID> Hunters = new HashSet<>();
    public static Location LatestRunnerPortal = null;

    public static void giveCompass(Player p) {
        if (p.getInventory().contains(Material.COMPASS)) {
            p.sendMessage(StringHelper.colorize(BroadcastHelper.BroadcastPrefix + "&cNOTE: You were given a compass but you already had one!"));
            return;
        }
        p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
    }

    public static void giveAllHuntersACompass() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!Hunters.isEmpty() && Hunters.contains(p.getUniqueId())) {
                giveCompass(p);
            }
        }

    }

    /**
     * Removes a runner from the list
     *
     * @param uid
     */
    public static void removeRunner(UUID uid) {
        if (!Runners.isEmpty()) {
            Runners.remove(uid);
        }
    }

    /**
     * Removes a runner from the list
     *
     * @param uid
     */
    public static void addRunner(UUID uid) {
        Runners.add(uid);
    }

    public static void addHunter(UUID uid) {
        Hunters.add(uid);
    }

    /**
     * Picks a random hunter to become the runner.
     * Prioritize players in The End
     *
     * @return Player to be the runner
     */
    public static UUID getRandomRunner() {
        ArrayList<UUID> options = new ArrayList<>();
        ArrayList<UUID> endOptions = new ArrayList<>();
        Random r = new Random();
        // Add all the non-runners to a list
        for (Player p : Bukkit.getOnlinePlayers()) {
            UUID curr = p.getUniqueId();
            if (!Runners.contains(curr) && !Hunters.contains(curr)) {
                if (p.getWorld().getEnvironment() == THE_END) {
                    endOptions.add(curr);
                }
                options.add(curr);
            }
        }
        // Prioritize players stuck in The End
        // This could be configurable, but makes the game much more smooth overall
        if (endOptions.size() > 0) {
            return endOptions.get(r.nextInt(options.size()));
        }
        return options.get(r.nextInt(options.size()));
    }
}
