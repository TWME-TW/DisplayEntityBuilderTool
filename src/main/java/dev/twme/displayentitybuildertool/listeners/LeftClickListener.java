package dev.twme.displayentitybuildertool.listeners;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class LeftClickListener implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onLeftClick(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if (player.getOpenInventory().getTopInventory().getType() == InventoryType.CHEST)
	    return;
	if (!event.getAction().equals(Action.LEFT_CLICK_AIR) && !event.getAction().equals(Action.LEFT_CLICK_BLOCK))
	    return;

	if (!player.hasPermission("displayentitybuildtool.break"))
	    return;

	// Log.log("LeftClickListener Pass");

	World world = player.getWorld();
	Location eyeLocation = player.getEyeLocation();
	Vector direction = player.getEyeLocation().getDirection();

	RayTraceResult entityRTR = world.rayTraceEntities(eyeLocation, direction, 4, 0.6,
		p -> !player.getUniqueId().equals(p.getUniqueId()));
	RayTraceResult blockRTR = world.rayTraceBlocks(eyeLocation, direction, 4, FluidCollisionMode.ALWAYS, false);

	if (entityRTR != null && blockRTR != null) {
	    EntityType et = entityRTR.getHitEntity().getType();
	    if (entityRTR.getHitEntity().getLocation().distance(eyeLocation) < blockRTR.getHitBlock().getLocation()
		    .distance(eyeLocation)) {
		if (et.equals(EntityType.ITEM_DISPLAY) || et.equals(EntityType.BLOCK_DISPLAY)) {
		    entityRTR.getHitEntity().remove();
		    player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 1f);
		    event.setCancelled(true);
		    return;
		}
	    }
	} else if (entityRTR != null) {
	    EntityType et = entityRTR.getHitEntity().getType();
	    if (et.equals(EntityType.ITEM_DISPLAY) || et.equals(EntityType.BLOCK_DISPLAY)) {
		entityRTR.getHitEntity().remove();
		player.playSound(player.getLocation(), Sound.BLOCK_STONE_BREAK, 1f, 1f);
		event.setCancelled(true);
		return;
	    }
	}
    }
}
