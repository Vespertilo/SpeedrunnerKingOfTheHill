package me.specnr.manhuntkoth.helpers;

import org.bukkit.ChatColor;

public class StringHelper {
    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&',s);
    }
}
