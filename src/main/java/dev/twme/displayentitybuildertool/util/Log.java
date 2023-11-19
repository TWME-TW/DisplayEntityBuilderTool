package dev.twme.displayentitybuildertool.util;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Log {
    public static void log(String messages) {
	Bukkit.getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', messages));
    }
}
