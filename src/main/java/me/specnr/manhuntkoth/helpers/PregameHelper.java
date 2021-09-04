package me.specnr.manhuntkoth.helpers;

import me.specnr.manhuntkoth.Manhunt;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class PregameHelper {
    public static boolean roundActive = false;

    public static void startRound(int headstart) {
        for (UUID uuid : HunterHelper.Runners) {
            Player curr = Bukkit.getPlayer(uuid);
            if (curr == null) {
                HunterHelper.removeRunner(uuid);
                BroadcastHelper.Broadcast("&cA runner was removed because they were offline!");
                return;
            }
            curr.teleport(curr.getWorld().getSpawnLocation());
        }
        new BukkitRunnable() {
            int i = headstart;
            int tick = 1;

            @Override
            public void run() {
                if (i <= 0) {
                    this.cancel();
                }
                for (UUID uuid : HunterHelper.Hunters) {
                    Player curr = Bukkit.getPlayer(uuid);
                    if (curr == null) {
                        HunterHelper.Hunters.remove(uuid);
                        BroadcastHelper.Broadcast("&cA hunter was removed because they were offline!");
                    } else {
                        curr.teleport(curr.getWorld().getSpawnLocation());
                        if (tick >= 20) {
                            curr.sendTitle(StringHelper.colorize("&a" + i), "", 1, 20, 1);
                            curr.playSound(curr.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                            curr.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1, false, false));
                            if(i<=3) {
                                for(UUID uid:HunterHelper.Runners) {
                                    Player p = Bukkit.getPlayer(uid);
                                    assert p != null;
                                    p.sendTitle(StringHelper.colorize("&a" + i), "", 1, 20, 1);
                                    p.playSound(curr.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                                }
                            }
                            if (i == 0) {
                                curr.sendTitle(StringHelper.colorize("&aGO!"), "", 1, 20, 1);
                                BroadcastHelper.Broadcast("&cThe hunters have been released!");
                                curr.playSound(curr.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
                                for(UUID uuid1:HunterHelper.Runners) {
                                    Player pl = Bukkit.getPlayer(uuid1);
                                    pl.sendTitle(StringHelper.colorize("&aGO!"), "", 1, 20, 1);
                                    BroadcastHelper.Broadcast("&cThe hunters have been released!");
                                    pl.playSound(curr.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 1);
                                }
                                roundActive = true;
                            }
                        }
                    }

                }
                if (tick >= 20) {
                    i--;
                    tick = 1;
                } else {
                    tick++;
                }

            }
        }.runTaskTimer(Manhunt.getInstance(), 0L, 1L);

    }
}
