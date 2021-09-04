package me.specnr.manhuntkoth.events;

import me.specnr.manhuntkoth.helpers.BroadcastHelper;
import me.specnr.manhuntkoth.helpers.HunterHelper;
import me.specnr.manhuntkoth.helpers.PregameHelper;
import me.specnr.manhuntkoth.helpers.StringHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnGeneralInteraction implements Listener {
    @EventHandler()
    /**
     * Updates the runner once the previous has died.
     */
    public void onDeath(PlayerDeathEvent evt) {
        if (Bukkit.getOnlinePlayers().size() > 1) {
            Player deadPlayer = evt.getEntity();
            if (HunterHelper.Runners.contains(deadPlayer.getUniqueId())) {
                if(PregameHelper.roundActive) {
                    evt.setDeathMessage("");
                    BroadcastHelper.Broadcast(StringHelper.colorize("&b&l&o" + deadPlayer.getName() + "&r&a died! Hunters win!"));
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.getInventory().clear();
                        p.teleport(p.getWorld().getSpawnLocation());
                        PregameHelper.roundActive = false;
                    }
                }
            }
        }
    }
}
