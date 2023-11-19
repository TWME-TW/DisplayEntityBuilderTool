package dev.twme.displayentitybuildertool.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import dev.twme.displayentitybuildertool.guis.slab.SlabGUI;

public class OpenStandSlabGuiCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
	    @NotNull String[] args) {
	if (!(sender instanceof Player)) {
	    sender.sendMessage("Only player can use this command!");
	    return false;
	}
	Player player = (Player) sender;
	if (player.hasPermission("displayentitybuildtool.gui.slab")) {
	    SlabGUI.openWindow(player);
	    return true;
	}

	return false;
    }
}
