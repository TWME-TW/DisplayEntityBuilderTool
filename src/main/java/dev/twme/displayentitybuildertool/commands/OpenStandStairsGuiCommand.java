package dev.twme.displayentitybuildertool.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import dev.twme.displayentitybuildertool.guis.stairs.StairsGUI;

public class OpenStandStairsGuiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
	    @NotNull String[] args) {
	if (!(sender instanceof Player)) {
	    sender.sendMessage("Only player can use this command!");
	    return false;
	}
	Player player = (Player) sender;
	if (player.hasPermission("displayentitybuildtool.gui.stairs")) {
	    StairsGUI.openWindow(player);
	    return true;
	}

	return false;
    }
}
