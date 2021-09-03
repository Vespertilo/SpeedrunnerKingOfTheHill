package me.specnr.manhuntkoth.events;

import me.specnr.manhuntkoth.helpers.HunterHelper;
import me.specnr.manhuntkoth.helpers.LocationHelper;
import me.specnr.manhuntkoth.helpers.PregameHelper;
import me.specnr.manhuntkoth.helpers.StringHelper;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

public class OnHunterInteraction implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent evt){
        Player player = evt.getPlayer();
        if(Objects.requireNonNull(player.getEquipment()).getItemInMainHand().getType() == Material.COMPASS){
            if (HunterHelper.Hunters.contains(player.getUniqueId())) {
                if(PregameHelper.roundActive) {
                    LocationHelper.updateHunterCompass(player);
                } else {
                    player.sendMessage(StringHelper.colorize("&cThe round is not active! Please wait for the round to begin."));
                }
            } else {
                player.sendMessage(StringHelper.colorize("&cYou are not a hunter! Please wait for the next round to start."));
                player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON,1,1);
            }
        }
    }

    @EventHandler
    /**
     * Gives a Compass to respawned Hunters
     */
    public void onPlayerRespawn(PlayerRespawnEvent evt){
        if(HunterHelper.Runners.size() > 0 && !HunterHelper.Runners.contains(evt.getPlayer().getUniqueId())){
            HunterHelper.giveCompass(evt.getPlayer());
        }
    }
}
