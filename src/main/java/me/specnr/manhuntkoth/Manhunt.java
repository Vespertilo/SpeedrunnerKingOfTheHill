package me.specnr.manhuntkoth;

import me.specnr.manhuntkoth.commands.*;
import me.specnr.manhuntkoth.events.OnGeneralInteraction;
import me.specnr.manhuntkoth.events.OnHunterInteraction;
import me.specnr.manhuntkoth.events.OnRunnerInteraction;
import org.bukkit.plugin.java.JavaPlugin;

public final class Manhunt extends JavaPlugin {

    private static Manhunt instance;

    /**
     * todo THIS IS A FORK OF SPECNR's KOTH plugin, just cleaned up and made into base manhunt by me.
     */
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new OnGeneralInteraction(), this);
        getServer().getPluginManager().registerEvents(new OnHunterInteraction(), this);
        getServer().getPluginManager().registerEvents(new OnRunnerInteraction(), this);
        getCommand("manhunt-quickstart").setExecutor(new Command_ManhuntQuickstart());
        getCommand("quickstart").setExecutor(new Command_ManhuntQuickstart());
        getCommand("manhunt-runner").setExecutor(new Command_ManhuntRunner());
        getCommand("runner").setExecutor(new Command_ManhuntRunner());
        getCommand("manhunt-hunter").setExecutor(new Command_ManhuntHunter());
        getCommand("hunter").setExecutor(new Command_ManhuntHunter());
        getCommand("manhunt-r").setExecutor(new Command_ManhuntStartRandom());
        getCommand("manhunt-reset").setExecutor(new Command_ManhuntReset());
        getCommand("runners").setExecutor(new Command_GetRunners());
        System.out.println("[Manhunt] Minecraft Manhunt Loaded");
    }

    public static Manhunt getInstance() {
        return instance;
    }
}
