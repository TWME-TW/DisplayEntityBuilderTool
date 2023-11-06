package dev.twme.displayentitybuildertool.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.logging.Level;

public class Log {
    public static void log(String messages){
        Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&',messages));
    }
}
